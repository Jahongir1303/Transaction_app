package com.jahongir.mini_transaction.dtos.transaction;

import lombok.*;

import java.util.UUID;

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
    private UUID transactionId;
}
