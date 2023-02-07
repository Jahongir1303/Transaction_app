package com.jahongir.mini_transaction.service;

import com.jahongir.mini_transaction.domains.Card;
import com.jahongir.mini_transaction.domains.Transaction;
import com.jahongir.mini_transaction.dtos.transaction.ConfirmResponse;
import com.jahongir.mini_transaction.dtos.transaction.HoldRequest;
import com.jahongir.mini_transaction.enums.CardType;
import com.jahongir.mini_transaction.enums.TransactionStat;
import com.jahongir.mini_transaction.exceptions.GenericRunTimeException;
import com.jahongir.mini_transaction.mappers.TransactionMapper;
import com.jahongir.mini_transaction.repository.CardRepository;
import com.jahongir.mini_transaction.repository.TransactionRepository;
import com.jahongir.mini_transaction.security.UserDetailsImpl;
import com.jahongir.mini_transaction.service.base.AbstractService;
import com.jahongir.mini_transaction.service.base.GenericCreateService;
import com.jahongir.mini_transaction.utils.TransactionConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class TransactionService extends AbstractService<TransactionRepository, TransactionMapper> implements GenericCreateService<UUID, HoldRequest> {
    private final CardRepository cardRepository;

    public TransactionService(TransactionRepository repository, TransactionMapper transactionMapper, CardRepository cardRepository) {
        super(repository, transactionMapper);
        this.cardRepository = cardRepository;
    }

    @Override
    public UUID create(HoldRequest holdRequest) {
        Long senderCardId = holdRequest.getSenderCardId();
        Optional<Card> optionalSenderCard = cardRepository.findById(senderCardId);

        if (optionalSenderCard.isEmpty()) {
            throw new GenericRunTimeException("There is not any card with given card id", HttpStatus.BAD_REQUEST.value());
        }

        Card senderCard = optionalSenderCard.get();
        UUID cardUserId = senderCard.getUser().getId();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        UUID userId = principal.getUser().getId();

        if (!cardUserId.equals(userId)) {
            throw new GenericRunTimeException("This card does not belong to the current user", HttpStatus.BAD_REQUEST.value());
        }


        Long senderCardBalance = senderCard.getBalance();

        BigDecimal senderAmount = holdRequest.getSenderAmount();
        long senderAmountLong = senderAmount.multiply(TransactionConstants.CONVERSIONNUM).longValue();

        if (senderCardBalance < senderAmountLong) {
            throw new GenericRunTimeException("Your balance is not enough to accomplish the transaction", HttpStatus.BAD_REQUEST.value());
        }

        Optional<Card> optionalReceiverCard = cardRepository.findById(holdRequest.getReceiverCardId());

        if (optionalReceiverCard.isEmpty()) {
            throw new GenericRunTimeException("There is not any card with given card id", HttpStatus.BAD_REQUEST.value());
        }

        CardType senderCardtype = optionalSenderCard.get().getType();
        CardType receiverCardType = optionalReceiverCard.get().getType();
        if (senderCardtype.name().equals(CardType.HUMO.name()) && receiverCardType.name().equals(CardType.VISA.name())) {
            if (senderAmountLong < TransactionConstants.UZSTOUSD) {
                throw new GenericRunTimeException("Please enter the sum which is higher than 1.13 sum", HttpStatus.BAD_REQUEST.value());
            }
        }

        Transaction newTransaction = mapper.fromDto(holdRequest);
        newTransaction.setSenderAmount(senderCardBalance);
        newTransaction.setReceiverAmount(optionalReceiverCard.get().getBalance());
        newTransaction.setStatus(TransactionStat.NEW);

        repository.save(newTransaction);

        return newTransaction.getId();
    }

    public ResponseEntity<ConfirmResponse> confirm(UUID transactionId) {
        return null;
    }
}
