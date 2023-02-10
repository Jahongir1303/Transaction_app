package com.jahongir.mini_transaction.dtos.transaction;

import com.jahongir.mini_transaction.enums.TransactionStat;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jahongir
 * @created 07/02/23 - 11:16
 * @project Mini_transaction/IntelliJ IDEA
 */
@Getter
@Setter
public class ConfirmResponse {
    private Long senderCardId;

    private Long receiverCardId;

    private Long senderAmount;

    private Long receiverAmount;

    private TransactionStat status;

    private Long time;
}
