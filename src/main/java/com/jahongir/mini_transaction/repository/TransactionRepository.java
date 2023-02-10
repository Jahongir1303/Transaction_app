package com.jahongir.mini_transaction.repository;

import com.jahongir.mini_transaction.domains.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

/**
 * @author jahongir
 * @created 01/02/23 - 01:06
 * @project Mini_transaction/IntelliJ IDEA
 */

public interface TransactionRepository extends JpaRepository<Transaction, UUID>, GenericRepository {
    @Override
    @Query("select t from Transaction t where t.id = :transactionId and t.status='NEW'")
    Optional<Transaction> findById(@Param("transactionId") UUID transactionId);
}
