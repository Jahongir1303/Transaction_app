package com.jahongir.mini_transaction.dtos.jwt;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jahongir
 * @created 04/02/23 - 01:59
 * @project Mini_transaction/IntelliJ IDEA
 */
@Getter
@Setter
public class TokenRefreshRequest {
    private String refreshToken;
}
