package com.jahongir.mini_transaction.dtos.jwt;

import com.jahongir.mini_transaction.dtos.base.Dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jahongir
 * @created 04/02/23 - 01:59
 * @project Mini_transaction/IntelliJ IDEA
 */
@Getter
@Setter
public class TokenRefreshRequest implements Dto {
    @NotBlank(message = "Refresh token must not be null or empty")
    private String refreshToken;

}
