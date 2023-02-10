package com.jahongir.mini_transaction.controller;

import com.jahongir.mini_transaction.dtos.transaction.ConfirmResponse;
import com.jahongir.mini_transaction.dtos.transaction.HoldRequest;
import com.jahongir.mini_transaction.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author jahongir
 * @created 05/02/23 - 15:21
 * @project Mini_transaction/IntelliJ IDEA
 */
@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
public class TransactionController extends ApiController<TransactionService> {
    public TransactionController(TransactionService service) {
        super(service);
    }

    @PostMapping(API + V1 + "/transaction/hold")
    public ResponseEntity<UUID> hold(@Valid @RequestBody HoldRequest holdRequest) {
        UUID transactionId = service.create(holdRequest);
        return ResponseEntity.ok(transactionId);
    }

    @PostMapping(API + V1 + "/transaction/confirm/{transactionId}")
    public ResponseEntity<ConfirmResponse> confirm(@PathVariable @NotBlank UUID transactionId) {
        ConfirmResponse confirmResponse = service.confirm(transactionId);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(confirmResponse);
    }
}
