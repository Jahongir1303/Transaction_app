package com.jahongir.mini_transaction.repository;

import com.jahongir.mini_transaction.domains.RefreshToken;
import com.jahongir.mini_transaction.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

/**
 * @author jahongir
 * @created 04/02/23 - 02:05
 * @project Mini_transaction/IntelliJ IDEA
 */

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    @Modifying
    int deleteByUser(User user);
}
