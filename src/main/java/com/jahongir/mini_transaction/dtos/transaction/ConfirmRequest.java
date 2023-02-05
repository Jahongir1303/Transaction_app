package com.jahongir.mini_transaction.dtos.transaction;

import lombok.*;

/**
 * @author jahongir
 * @created 05/02/23 - 15:39
 * @project Mini_transaction/IntelliJ IDEA
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ConfirmRequest {
    private Long transactionId;
}
