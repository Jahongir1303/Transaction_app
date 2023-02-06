package com.jahongir.mini_transaction.service;

import com.jahongir.mini_transaction.domains.Card;
import com.jahongir.mini_transaction.domains.User;
import com.jahongir.mini_transaction.dtos.card.CardAddRequest;
import com.jahongir.mini_transaction.dtos.card.CardResponse;
import com.jahongir.mini_transaction.enums.CardType;
import com.jahongir.mini_transaction.enums.CurrencyType;
import com.jahongir.mini_transaction.exceptions.GenericRunTimeException;
import com.jahongir.mini_transaction.mappers.CardMapper;
import com.jahongir.mini_transaction.repository.CardRepository;
import com.jahongir.mini_transaction.security.UserDetailsImpl;
import com.jahongir.mini_transaction.service.base.AbstractService;
import com.jahongir.mini_transaction.service.base.GenericCreateService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author jahongir
 * @created 05/02/23 - 15:48
 * @project Mini_transaction/IntelliJ IDEA
 */
@Service
public class CardService extends AbstractService<CardRepository, CardMapper> implements GenericCreateService<Long, CardAddRequest> {
    public CardService(CardRepository repository, CardMapper cardMapper) {
        super(repository, cardMapper);
    }

    @Override
    public Long create(CardAddRequest cardAddRequest) {
        String cardNumber = cardAddRequest.getCardNumber();
        Boolean exists = repository.existsByCardNumber(cardNumber);
        if (exists) {
            throw new GenericRunTimeException("Card with such card number has already been added", HttpStatus.BAD_REQUEST.value());
        }
        Card card = mapper.fromCreateDto(cardAddRequest);
        String typeDetection = card.getCardNumber();
        if (typeDetection.startsWith("9860")) {
            card.setType(CardType.HUMO);
            card.setCurrency(CurrencyType.UZS);
        } else if (typeDetection.startsWith("4200")) {
            card.setType(CardType.VISA);
            card.setCurrency(CurrencyType.USD);
        } else {
            throw new GenericRunTimeException("Wrong card number has been added", HttpStatus.BAD_REQUEST.value());
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        card.setUser(principal.getUser());
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
}
