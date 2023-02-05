package com.jahongir.mini_transaction.dtos.transaction;

import com.jahongir.mini_transaction.dtos.base.Dto;
import lombok.*;

/**
 * @author jahongir
 * @created 05/02/23 - 15:28
 * @project Mini_transaction/IntelliJ IDEA
 */

@Getter
@Setter
public class HoldRequest implements Dto {
    private Long senderCardId;
    private Long receiverCardId;
    private Long senderAmount;
}
