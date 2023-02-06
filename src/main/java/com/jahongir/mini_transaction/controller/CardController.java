package com.jahongir.mini_transaction.controller;

import com.jahongir.mini_transaction.domains.Card;
import com.jahongir.mini_transaction.dtos.card.CardAddRequest;
import com.jahongir.mini_transaction.dtos.card.CardResponse;
import com.jahongir.mini_transaction.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author jahongir
 * @created 05/02/23 - 15:47
 * @project Mini_transaction/IntelliJ IDEA
 */
@RestController
public class CardController extends ApiController<CardService> {

    public CardController(CardService service) {
        super(service);
    }

    @PostMapping(API + V1 + "/card/add")
    public ResponseEntity<Long> add(@RequestBody CardAddRequest cardAddRequest) {
        Long cardId = service.create(cardAddRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(cardId);
    }

    @GetMapping(API + V1 + "/card/get/{userId}")
    public ResponseEntity<List<CardResponse>> getAllMyCards(@PathVariable("userId") UUID userId) {
        List<CardResponse> allMyCards = service.getAllMyCards(userId);
        return ResponseEntity.ok(allMyCards);
    }
}
