package com.jahongir.mini_transaction.dtos.card;

import com.jahongir.mini_transaction.dtos.base.Dto;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author jahongir
 * @created 05/02/23 - 15:50
 * @project Mini_transaction/IntelliJ IDEA
 */
@Getter
@Setter
public class CardAddRequest implements Dto {
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 12, fraction = 2, message = "Wrong format is entered")
    private BigDecimal balance;
    @NotBlank(message = "Name can not be empty or null")
    @Size(max = 255, message = "Field size should not be grater than 255 in length")
    private String name;
    @Pattern(regexp = "^(9860|4200)[0-9]{12}$", message = "Please enter a valid card number")
    private String cardNumber;
}
