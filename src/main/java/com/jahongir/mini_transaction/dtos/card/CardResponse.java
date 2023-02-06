package com.jahongir.mini_transaction.dtos.card;

import com.jahongir.mini_transaction.enums.CardType;
import lombok.*;

/**
 * @author jahongir
 * @created 05/02/23 - 15:55
 * @project Mini_transaction/IntelliJ IDEA
 */
@Getter
@Setter
public class CardResponse {
    private Long balance;
    private String name;
    private String cardNumber;
    private CardType type;
}
