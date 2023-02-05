package com.jahongir.mini_transaction.controller;

import com.jahongir.mini_transaction.service.TransactionService;
import org.springframework.web.bind.annotation.RestController;

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
}
