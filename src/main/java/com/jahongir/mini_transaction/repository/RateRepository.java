package com.jahongir.mini_transaction.repository;

import com.jahongir.mini_transaction.domains.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jahongir
 * @created 01/02/23 - 01:04
 * @project Mini_transaction/IntelliJ IDEA
 */

public interface RateRepository extends JpaRepository<Rate, Long>, GenericRepository {
}
