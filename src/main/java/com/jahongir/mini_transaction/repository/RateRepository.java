package com.jahongir.mini_transaction.repository;

import com.jahongir.mini_transaction.domains.Rate;
import com.jahongir.mini_transaction.enums.CurrencyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author jahongir
 * @created 01/02/23 - 01:04
 * @project Mini_transaction/IntelliJ IDEA
 */

public interface RateRepository extends JpaRepository<Rate, Long>, GenericRepository {
    Optional<Rate> findByToCurrency(CurrencyType toCurrency);

    Optional<Rate> findByFromCurrency(CurrencyType currency);
}
