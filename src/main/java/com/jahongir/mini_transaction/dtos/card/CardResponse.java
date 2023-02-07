package com.jahongir.mini_transaction.dtos.card;

import com.jahongir.mini_transaction.enums.CardType;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author jahongir
 * @created 05/02/23 - 15:55
 * @project Mini_transaction/IntelliJ IDEA
 */
@Getter
@Setter
public class CardResponse {
    private BigDecimal balance;
    private String name;
    private String cardNumber;
    private CardType type;
}
