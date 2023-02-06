package com.jahongir.mini_transaction.repository;

import com.jahongir.mini_transaction.domains.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author jahongir
 * @created 01/02/23 - 01:00
 * @project Mini_transaction/IntelliJ IDEA
 */

public interface CardRepository extends JpaRepository<Card, Long>, GenericRepository {
    Boolean existsByCardNumber(String cardNumber);

    Optional<List<Card>> findAllByUserId(UUID userId);
}
