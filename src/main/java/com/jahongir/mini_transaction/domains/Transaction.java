package com.jahongir.mini_transaction.domains;

import com.jahongir.mini_transaction.enums.TransactionStat;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Locale;
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
    @Nonnull
    private Long senderAmount;
    @Nonnull
    private Long receiverAmount;
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private TransactionStat status;
    @Column(nullable = false)
    private Timestamp timestamp;
}
