package com.jahongir.mini_transaction.service;

import com.jahongir.mini_transaction.domains.Card;
import com.jahongir.mini_transaction.domains.Rate;
import com.jahongir.mini_transaction.domains.Transaction;
import com.jahongir.mini_transaction.domains.User;
import com.jahongir.mini_transaction.dtos.transaction.ConfirmResponse;
import com.jahongir.mini_transaction.dtos.transaction.HoldRequest;
import com.jahongir.mini_transaction.enums.CardType;
import com.jahongir.mini_transaction.enums.TransactionStat;
import com.jahongir.mini_transaction.exceptions.GenericNotFoundException;
import com.jahongir.mini_transaction.exceptions.GenericRunTimeException;
import com.jahongir.mini_transaction.mappers.TransactionMapper;
import com.jahongir.mini_transaction.repository.CardRepository;
import com.jahongir.mini_transaction.repository.RateRepository;
import com.jahongir.mini_transaction.repository.TransactionRepository;
import com.jahongir.mini_transaction.security.CurrentUser;
import com.jahongir.mini_transaction.service.base.AbstractService;
import com.jahongir.mini_transaction.service.base.GenericCreateService;
import com.jahongir.mini_transaction.utils.TransactionConstants;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

/**
 * @author jahongir
 * @created 05/02/23 - 15:23
 * @project Mini_transaction/IntelliJ IDEA
 */
@Service
@Transactional
public class TransactionService extends AbstractService<TransactionRepository, TransactionMapper> implements GenericCreateService<UUID, HoldRequest> {
    private final CardRepository cardRepository;
    private final CurrentUser currentUser;
    private final CardService cardService;

    private final RateRepository rateRepository;

    public TransactionService(TransactionRepository repository, TransactionMapper transactionMapper, CardRepository cardRepository, CurrentUser currentUser, CardService cardService, RateRepository rateRepository) {
        super(repository, transactionMapper);
        this.cardRepository = cardRepository;
        this.currentUser = currentUser;
        this.cardService = cardService;
        this.rateRepository = rateRepository;
    }

    @Override
    public UUID create(HoldRequest holdRequest) {
        Card senderCard = cardService.getCardById(holdRequest.getSenderCardId());

        senderCardValidation(senderCard);

        BigDecimal senderAmount = holdRequest.getSenderAmount();
        long senderAmountLong = senderAmount.multiply(TransactionConstants.CONVERSIONNUM).longValue();

        senderCardBalanceChecking(senderCard, senderAmountLong);

        Card receiverCard = cardService.getCardById(holdRequest.getReceiverCardId());

        CardType senderCardtype = senderCard.getType();
        CardType receiverCardType = receiverCard.getType();

        UUID transactionId;
        if (!senderCardtype.equals(receiverCardType)) {
            transactionId = transactionWithDifferentCardTypes(senderCard, receiverCard, senderAmountLong);
        } else {
            Transaction transaction = Transaction.builder()
                    .senderCardId(senderCard.getId())
                    .receiverCardId(receiverCard.getId())
                    .senderAmount(senderAmountLong)
                    .receiverAmount(senderAmountLong)
                    .build();
            repository.save(transaction);
            transactionId = transaction.getId();
        }

        return transactionId;
    }

    public synchronized ConfirmResponse confirm(UUID transactionId) throws GenericNotFoundException {
        Transaction transaction = findById(transactionId);

        Long senderCardId = transaction.getSenderCardId();
        Card senderCard = cardService.getCardById(senderCardId);

        Long receiverCardId = transaction.getReceiverCardId();
        Card receiverCard = cardService.getCardById(receiverCardId);

        Long senderAmount = transaction.getSenderAmount();

        Long senderCardBalance = senderCard.getBalance();
        if (senderCardBalance < senderAmount) {
            transaction.setStatus(TransactionStat.ERROR);
            repository.save(transaction);
        } else {
            senderCard.setBalance(senderCard.getBalance() - senderAmount);
            cardRepository.save(senderCard);

            Long receiverAmount = transaction.getReceiverAmount();

            receiverCard.setBalance(receiverCard.getBalance() + receiverAmount);
            cardRepository.save(receiverCard);

            transaction.setStatus(TransactionStat.SUCCESS);
            repository.save(transaction);
        }

        return mapper.fromDto(transaction);
    }

    private void senderCardValidation(Card senderCard) {

        UUID cardUserId = senderCard.getUser().getId();
        System.out.println("cardUserId = " + cardUserId);

        User user = currentUser.getCurrentUser();
        UUID currentUserId = user.getId();
        System.out.println("currentUserId = " + currentUserId);

        if (!cardUserId.equals(currentUserId)) {
            throw new GenericRunTimeException("This card does not belong to the current user", HttpStatus.BAD_REQUEST);
        }
    }

    private void senderCardBalanceChecking(Card senderCard, Long senderAmountLong) {
        Long senderCardBalance = senderCard.getBalance();

        if (senderCardBalance < senderAmountLong) {
            throw new GenericRunTimeException("Your balance is not enough to accomplish the transaction", HttpStatus.BAD_REQUEST);
        }
    }

    private UUID transactionWithDifferentCardTypes(Card senderCard, Card receiverCard, long senderAmountLong) {
        Optional<Rate> optionalRate = rateRepository.findByFromCurrency(senderCard.getCurrency());

        if (optionalRate.isEmpty()) {
            throw new GenericNotFoundException("Not found rate with such currency", HttpStatus.NOT_FOUND);
        }

        Rate rate = optionalRate.get();
        Long rateValue = rate.getRate();
        long receiverAmountLong;
        long leftValue = 0L;
        if (rateValue < 0) {
            rateValue = -rateValue;
            receiverAmountLong = senderAmountLong / rateValue;
            leftValue = senderAmountLong % rateValue;
            senderAmountLong = senderAmountLong - leftValue;
            if (senderAmountLong < rateValue) {
                throw new GenericRunTimeException("Please enter the sum which is higher than 113.47 sum", HttpStatus.BAD_REQUEST);
            }
        } else {
            receiverAmountLong = senderAmountLong * rateValue;
        }

        Transaction newTransaction = Transaction.builder()
                .senderCardId(senderCard.getId())
                .receiverCardId(receiverCard.getId())
                .senderAmount(senderAmountLong)
                .receiverAmount(receiverAmountLong)
                .build();

        repository.save(newTransaction);

        return newTransaction.getId();
    }

    private Transaction findById(UUID transactionId) {
        Optional<Transaction> transactionById = repository.findById(transactionId);

        return transactionById.orElseThrow(() -> {
            throw new GenericNotFoundException("Transaction not found with status 'NEW' for the corresponding id", HttpStatus.NOT_FOUND);
        });
    }
}
