package com.jahongir.mini_transaction.dtos.jwt;

import com.jahongir.mini_transaction.dtos.base.Dto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

/**
 * @author jahongir
 * @created 02/02/23 - 14:45
 * @project Mini_transaction/IntelliJ IDEA
 */
@Getter
@Setter
@ToString
public class JwtResponse implements Dto {
    private String token;
    private final String type = "Bearer";
    private String refreshToken;
    private UUID id;
    private String phoneNumber;

    public JwtResponse(String token, String refreshToken, UUID id, String phoneNumber) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.id = id;
        this.phoneNumber = phoneNumber;
    }

}
