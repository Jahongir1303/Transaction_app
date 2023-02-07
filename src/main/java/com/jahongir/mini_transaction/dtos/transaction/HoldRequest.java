package com.jahongir.mini_transaction.dtos.transaction;

import com.jahongir.mini_transaction.dtos.base.Dto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author jahongir
 * @created 05/02/23 - 15:28
 * @project Mini_transaction/IntelliJ IDEA
 */

@Getter
@Setter
public class HoldRequest implements Dto {
    @Min(value = 0, message = "Id must be at least 0")
    private Long senderCardId;
    @Min(value = 0, message = "Id must be at least 0")
    private Long receiverCardId;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal senderAmount;
}
