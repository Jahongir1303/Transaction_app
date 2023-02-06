package com.jahongir.mini_transaction.repository;

import com.jahongir.mini_transaction.domains.Card;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jahongir
 * @created 01/02/23 - 01:00
 * @project Mini_transaction/IntelliJ IDEA
 */

public interface CardRepository extends JpaRepository<Card, Long>, GenericRepository {
    Boolean existsByCardNumber(String cardNumber);
}
