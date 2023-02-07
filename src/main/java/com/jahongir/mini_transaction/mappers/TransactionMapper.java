package com.jahongir.mini_transaction.mappers;

import com.jahongir.mini_transaction.domains.Transaction;
import com.jahongir.mini_transaction.dtos.transaction.HoldRequest;
import org.mapstruct.Mapper;

/**
 * @author jahongir
 * @created 05/02/23 - 15:26
 * @project Mini_transaction/IntelliJ IDEA
 */
@Mapper(componentModel = "spring")
public interface TransactionMapper extends BaseMapper {
    Transaction fromDto(HoldRequest holdRequest);
}
