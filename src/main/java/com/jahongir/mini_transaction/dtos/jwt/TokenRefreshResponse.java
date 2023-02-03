package com.jahongir.mini_transaction.dtos.jwt;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jahongir
 * @created 04/02/23 - 02:02
 * @project Mini_transaction/IntelliJ IDEA
 */
@Getter
@Setter
public class TokenRefreshResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";

    public TokenRefreshResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
