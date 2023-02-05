package com.jahongir.mini_transaction.service;

import com.jahongir.mini_transaction.dtos.card.CardCreateDTO;
import com.jahongir.mini_transaction.mappers.CardMapper;
import com.jahongir.mini_transaction.repository.CardRepository;
import com.jahongir.mini_transaction.service.base.AbstractService;
import com.jahongir.mini_transaction.service.base.GenericCreateService;
import org.springframework.stereotype.Service;

/**
 * @author jahongir
 * @created 05/02/23 - 15:48
 * @project Mini_transaction/IntelliJ IDEA
 */
@Service
public class CardService extends AbstractService<CardRepository, CardMapper> implements GenericCreateService<Long, CardCreateDTO> {
    public CardService(CardRepository repository, CardMapper cardMapper) {
        super(repository, cardMapper);
    }

    @Override
    public Long create(CardCreateDTO dto) {
        return null;
    }
}
