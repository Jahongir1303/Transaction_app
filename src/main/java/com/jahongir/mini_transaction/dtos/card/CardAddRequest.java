package com.jahongir.mini_transaction.dtos.card;

import com.jahongir.mini_transaction.dtos.base.Dto;
import lombok.*;

/**
 * @author jahongir
 * @created 05/02/23 - 15:50
 * @project Mini_transaction/IntelliJ IDEA
 */
@Getter
@Setter
public class CardAddRequest implements Dto {
    private Long balance;
    private String name;
    private String cardNumber;
}
