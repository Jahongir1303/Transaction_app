package com.jahongir.mini_transaction.mappers;

import com.jahongir.mini_transaction.domains.Card;
import com.jahongir.mini_transaction.dtos.card.CardAddRequest;
import com.jahongir.mini_transaction.dtos.card.CardResponse;
import org.hibernate.annotations.Target;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author jahongir
 * @created 05/02/23 - 15:51
 * @project Mini_transaction/IntelliJ IDEA
 */
@Mapper(componentModel = "spring")
public interface CardMapper extends BaseMapper {
    @Mapping(target = "balance", ignore = true)
    Card fromCreateDto(CardAddRequest cardAddRequest);

    CardResponse toInfoDto(Card card);

    List<CardResponse> toDto(List<Card> card);


}
