package com.jahongir.mini_transaction.controller;

import com.jahongir.mini_transaction.dtos.transaction.ConfirmRequest;
import com.jahongir.mini_transaction.dtos.transaction.HoldRequest;
import com.jahongir.mini_transaction.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author jahongir
 * @created 05/02/23 - 15:21
 * @project Mini_transaction/IntelliJ IDEA
 */
@RestController
public class TransactionController extends ApiController<TransactionService> {
    public TransactionController(TransactionService service) {
        super(service);
    }

    @PostMapping(API + V1 + "/transaction/hold")
    public ResponseEntity<UUID> hold(@RequestBody HoldRequest holdRequest) {
        return null;
    }

    @PostMapping(API + V1 + "/transaction/confirm")
    public ResponseEntity<UUID> hold(@RequestBody ConfirmRequest confirmRequest) {
        return null;
    }
}
