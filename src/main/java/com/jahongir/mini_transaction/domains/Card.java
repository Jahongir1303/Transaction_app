package com.jahongir.mini_transaction.domains;

import com.jahongir.mini_transaction.enums.CardType;
import com.jahongir.mini_transaction.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author jahongir
 * @created 01/02/23 - 00:14
 * @project Mini_transaction/IntelliJ IDEA
 */
@Entity
@Table(name = "card")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_id_seq_gen")
    @SequenceGenerator(name = "card_id_seq_gen", sequenceName = "card_id_sequence", allocationSize = 1)
    private Long id;
    private Long balance;
    private String name;
    @Column(unique = true, nullable = false, length = 16)
    private String cardNumber;
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private CardType type;
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
