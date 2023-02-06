package com.jahongir.mini_transaction.mappers;

import com.jahongir.mini_transaction.domains.Card;
import com.jahongir.mini_transaction.dtos.card.CardAddRequest;
import org.mapstruct.Mapper;

/**
 * @author jahongir
 * @created 05/02/23 - 15:51
 * @project Mini_transaction/IntelliJ IDEA
 */
@Mapper(componentModel = "spring")
public interface CardMapper extends BaseMapper {
    Card fromCreateDto(CardAddRequest cardAddRequest);
}
