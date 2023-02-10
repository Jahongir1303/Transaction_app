package com.jahongir.mini_transaction.mappers;

import com.jahongir.mini_transaction.domains.Card;
import com.jahongir.mini_transaction.dtos.card.CardAddRequest;
import com.jahongir.mini_transaction.dtos.card.CardResponse;
import com.jahongir.mini_transaction.utils.TransactionConstants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
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

    @Mapping(target = "balance", source = "balance", qualifiedByName = "convertLongToBigDecimal")
    CardResponse toInfoDto(Card card);

    List<CardResponse> toDto(List<Card> card);

    @Named("convertLongToBigDecimal")
    static BigDecimal convertLongToBigDecimal(Long balanceInLong) {
        BigDecimal rawBalance = BigDecimal.valueOf(balanceInLong);
        return rawBalance.divide(TransactionConstants.CONVERSIONNUM);
    }
}
