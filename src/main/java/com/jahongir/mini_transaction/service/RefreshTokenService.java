package com.jahongir.mini_transaction.service;

import com.jahongir.mini_transaction.domains.RefreshToken;
import com.jahongir.mini_transaction.dtos.jwt.TokenRefreshRequest;
import com.jahongir.mini_transaction.dtos.jwt.TokenRefreshResponse;
import com.jahongir.mini_transaction.exceptions.TokenRefreshException;
import com.jahongir.mini_transaction.repository.RefreshTokenRepository;
import com.jahongir.mini_transaction.repository.UserRepository;

import com.jahongir.mini_transaction.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

/**
 * @author jahongir
 * @created 04/02/23 - 02:07
 * @project Mini_transaction/IntelliJ IDEA
 */
@Service
public class RefreshTokenService {
    @Value("${tune.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;


    private final RefreshTokenRepository refreshTokenRepository;


    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository, JwtUtils jwtUtils) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(UUID userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Transactional
    public int deleteByUserId(UUID userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }

    public TokenRefreshResponse refreshToken(TokenRefreshRequest tokenRefreshRequest) {
        String requestRefreshToken = tokenRefreshRequest.getRefreshToken();

        return findByToken(requestRefreshToken)
                .map(this::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromPhoneNumber(user.getPhoneNumber());
                    return new TokenRefreshResponse(token, requestRefreshToken);
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
}
