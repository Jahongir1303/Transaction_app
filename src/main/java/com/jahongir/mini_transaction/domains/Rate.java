package com.jahongir.mini_transaction.domains;

import com.jahongir.mini_transaction.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author jahongir
 * @created 01/02/23 - 00:08
 * @project Mini_transaction/IntelliJ IDEA
 */
@Entity
@Table(name = "rate")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rate_id_seq_gen")
    @SequenceGenerator(name = "rate_id_seq_gen", sequenceName = "rate_id_sequence", allocationSize = 1)
    private Long id;
    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private CurrencyType fromCurrency;
    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private CurrencyType toCurrency;
    private Long rate;
}
