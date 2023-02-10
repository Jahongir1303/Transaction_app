package com.jahongir.mini_transaction.domains;

import com.jahongir.mini_transaction.enums.TransactionStat;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * @author jahongir
 * @created 01/02/23 - 00:35
 * @project Mini_transaction/IntelliJ IDEA
 */
@Entity
@Table(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private Long senderCardId;
    @Column(nullable = false)
    private Long receiverCardId;
    @Column(nullable = false)
    private Long senderAmount;
    @Column(nullable = false)
    private Long receiverAmount;
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    @Builder.Default
    private TransactionStat status = TransactionStat.NEW;
    @Column(nullable = false)
    @Builder.Default
    private Long time = System.currentTimeMillis();
}
