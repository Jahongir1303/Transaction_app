package com.jahongir.mini_transaction.controller;

import com.jahongir.mini_transaction.dtos.jwt.TokenRefreshRequest;
import com.jahongir.mini_transaction.dtos.jwt.TokenRefreshResponse;
import com.jahongir.mini_transaction.dtos.user.LoginRequest;
import com.jahongir.mini_transaction.dtos.jwt.AccessTokenResponse;
import com.jahongir.mini_transaction.dtos.user.RegisterRequest;
import com.jahongir.mini_transaction.service.RefreshTokenService;
import com.jahongir.mini_transaction.service.UserDetailsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author jahongir
 * @created 02/02/23 - 14:37
 * @project Mini_transaction/IntelliJ IDEA
 */
@RestController
public class AuthUserController extends ApiController<UserDetailsServiceImpl> {

    private final RefreshTokenService refreshTokenService;

    public AuthUserController(UserDetailsServiceImpl service, RefreshTokenService refreshTokenService) {
        super(service);
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping(value = API + V1 + "/user/login")
    public ResponseEntity<AccessTokenResponse> login(@RequestBody LoginRequest loginRequest) {
        AccessTokenResponse jwtResponse = service.login(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping(value = API + V1 + "/user/register")
    public ResponseEntity<UUID> register(@RequestBody RegisterRequest registerRequest) {
        UUID userId = service.create(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userId);
    }

    @PostMapping(value = API + V1 + "/refreshtoken")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@RequestBody TokenRefreshRequest tokenRefreshRequest) {
        TokenRefreshResponse tokenRefreshResponse = refreshTokenService.refreshToken(tokenRefreshRequest);
        return ResponseEntity.ok(tokenRefreshResponse);
    }

    @PostMapping(value = API + V1 + "/user/delete/{phoneNumber}")
    public ResponseEntity<String> delete(@PathVariable("phoneNumber") String phoneNumber) {
        String message = service.deleteUserByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(message);
    }
}
