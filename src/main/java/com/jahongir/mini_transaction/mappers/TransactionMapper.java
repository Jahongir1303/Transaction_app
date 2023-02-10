package com.jahongir.mini_transaction.mappers;

import com.jahongir.mini_transaction.domains.Transaction;
import com.jahongir.mini_transaction.dtos.transaction.ConfirmResponse;
import com.jahongir.mini_transaction.dtos.transaction.HoldRequest;
import com.jahongir.mini_transaction.utils.TransactionConstants;
import jakarta.transaction.Transactional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;

/**
 * @author jahongir
 * @created 05/02/23 - 15:26
 * @project Mini_transaction/IntelliJ IDEA
 */
@Mapper(componentModel = "spring")
public interface TransactionMapper extends BaseMapper {
    ConfirmResponse fromDto(Transaction transaction);
}
