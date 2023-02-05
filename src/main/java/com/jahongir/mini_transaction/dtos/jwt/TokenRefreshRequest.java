package com.jahongir.mini_transaction.dtos.jwt;

import com.jahongir.mini_transaction.dtos.base.Dto;
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
    private String refreshToken;


}
