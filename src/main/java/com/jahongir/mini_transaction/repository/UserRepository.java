package com.jahongir.mini_transaction.repository;

import com.jahongir.mini_transaction.domains.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author jahongir
 * @created 31/01/23 - 23:43
 * @project Mini_transaction/IntelliJ IDEA
 */

public interface UserRepository extends JpaRepository<User, UUID>, GenericRepository {
    Optional<User> findUserByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);
}
