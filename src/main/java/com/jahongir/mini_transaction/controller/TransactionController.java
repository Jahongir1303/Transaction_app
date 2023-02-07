package com.jahongir.mini_transaction.controller;

import com.jahongir.mini_transaction.dtos.transaction.ConfirmResponse;
import com.jahongir.mini_transaction.dtos.transaction.HoldRequest;
import com.jahongir.mini_transaction.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<UUID> hold(@Valid @RequestBody HoldRequest holdRequest) {
        UUID transactionId = service.create(holdRequest);
        return ResponseEntity.ok(transactionId);
    }

    @PostMapping(API + V1 + "/transaction/confirm")
    public ResponseEntity<ConfirmResponse> confirm(@PathVariable @NotNull UUID transactionId) {
        service.confirm(transactionId);
        return null;
    }
}
