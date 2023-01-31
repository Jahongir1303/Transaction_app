package com.jahongir.mini_transaction.repository;

import com.jahongir.mini_transaction.domains.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author jahongir
 * @created 01/02/23 - 01:06
 * @project Mini_transaction/IntelliJ IDEA
 */

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
