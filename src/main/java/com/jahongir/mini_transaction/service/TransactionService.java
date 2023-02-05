package com.jahongir.mini_transaction.service;

import com.jahongir.mini_transaction.dtos.transaction.HoldRequest;
import com.jahongir.mini_transaction.mappers.CardMapper;
import com.jahongir.mini_transaction.mappers.TransactionMapper;
import com.jahongir.mini_transaction.repository.TransactionRepository;
import com.jahongir.mini_transaction.service.base.AbstractService;
import com.jahongir.mini_transaction.service.base.GenericCreateService;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author jahongir
 * @created 05/02/23 - 15:23
 * @project Mini_transaction/IntelliJ IDEA
 */
@Service
public class TransactionService extends AbstractService<TransactionRepository, TransactionMapper> implements GenericCreateService<UUID, HoldRequest> {


    public TransactionService(TransactionRepository repository, TransactionMapper transactionMapper) {
        super(repository, transactionMapper);
    }

    @Override
    public UUID create(HoldRequest dto) {
        return null;
    }
}
