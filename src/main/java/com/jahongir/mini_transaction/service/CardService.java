package com.jahongir.mini_transaction.service;

import com.jahongir.mini_transaction.domains.Card;
import com.jahongir.mini_transaction.domains.User;
import com.jahongir.mini_transaction.dtos.card.CardAddRequest;
import com.jahongir.mini_transaction.dtos.card.CardResponse;
import com.jahongir.mini_transaction.enums.CardType;
import com.jahongir.mini_transaction.enums.CurrencyType;
import com.jahongir.mini_transaction.exceptions.GenericNotFoundException;
import com.jahongir.mini_transaction.exceptions.GenericRunTimeException;
import com.jahongir.mini_transaction.mappers.CardMapper;
import com.jahongir.mini_transaction.repository.CardRepository;
import com.jahongir.mini_transaction.security.CurrentUser;
import com.jahongir.mini_transaction.service.base.AbstractService;
import com.jahongir.mini_transaction.service.base.GenericCreateService;
import com.jahongir.mini_transaction.utils.TransactionConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author jahongir
 * @created 05/02/23 - 15:48
 * @project Mini_transaction/IntelliJ IDEA
 */
@Service
public class CardService extends AbstractService<CardRepository, CardMapper> implements GenericCreateService<Long, CardAddRequest> {
    private final CurrentUser currentUser;

    public CardService(CardRepository repository, CardMapper cardMapper, CurrentUser currentUser) {
        super(repository, cardMapper);
        this.currentUser = currentUser;
    }

    @Override
    public Long create(CardAddRequest cardAddRequest) {
        String cardNumber = cardAddRequest.getCardNumber();
        Boolean exists = repository.existsByCardNumber(cardNumber);
        if (exists) {
            throw new GenericRunTimeException("Card with such card number has already been added", HttpStatus.BAD_REQUEST);
        }

        Long tiyinOrCent = cardAddRequest.getBalance().multiply(TransactionConstants.CONVERSIONNUM).longValue();
        Card card = mapper.fromCreateDto(cardAddRequest);
        card.setBalance(tiyinOrCent);
        String typeDetection = card.getCardNumber();
        if (typeDetection.startsWith("9860")) {
            card.setType(CardType.HUMO);
            card.setCurrency(CurrencyType.UZS);
        } else if (typeDetection.startsWith("4200")) {
            card.setType(CardType.VISA);
            card.setCurrency(CurrencyType.USD);
        } else {
            throw new GenericRunTimeException("Wrong card number has been added", HttpStatus.BAD_REQUEST);
        }

        User currentUser = this.currentUser.getCurrentUser();
        card.setUser(currentUser);

        repository.save(card);
        return card.getId();
    }

    public List<CardResponse> getAllMyCards(UUID userId) {
        List<CardResponse> cardList;
        Optional<List<Card>> allCardsByUserId = repository.findAllByUserId(userId);
        if (allCardsByUserId.isPresent()) {
            cardList = mapper.toDto(allCardsByUserId.get());
        } else {
            return Collections.emptyList();
        }
        return cardList;
    }

    public CardResponse getCardInfo(String cardNumber) {
        CardResponse cardResponse;
        Optional<Card> byCardNumber = repository.findByCardNumber(cardNumber);
        if (byCardNumber.isPresent()) {
            Card card = byCardNumber.get();

            cardResponse = mapper.toInfoDto(card);
        } else {
            throw new GenericNotFoundException("Thres is not any card with such card id on database", HttpStatus.NOT_FOUND);
        }
        return cardResponse;
    }

    public Card getCardById(Long id) {
        Optional<Card> optionalCard = repository.findById(id);

        if (optionalCard.isEmpty()) {
            throw new GenericRunTimeException("There is not any card with given card id", HttpStatus.BAD_REQUEST);
        }

        return optionalCard.get();
    }
}
