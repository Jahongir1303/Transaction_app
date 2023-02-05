package com.jahongir.mini_transaction.controller;

import com.jahongir.mini_transaction.dtos.jwt.TokenRefreshRequest;
import com.jahongir.mini_transaction.dtos.jwt.TokenRefreshResponse;
import com.jahongir.mini_transaction.dtos.user.LoginRequest;
import com.jahongir.mini_transaction.dtos.jwt.JwtResponse;
import com.jahongir.mini_transaction.dtos.user.RegisterRequest;
import com.jahongir.mini_transaction.service.RefreshTokenService;
import com.jahongir.mini_transaction.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    RefreshTokenService refreshTokenService;

    public AuthUserController(UserDetailsServiceImpl service) {
        super(service);
    }

    @PostMapping(value = API + V1 + "/user/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(service.login(loginRequest));
    }

    @PostMapping(value = API + V1 + "/user/register")
    public ResponseEntity<UUID> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(registerRequest));
    }

    @PostMapping(value = API + V1 + "/refreshtoken")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@RequestBody TokenRefreshRequest tokenRefreshRequest) {
        return refreshTokenService.refreshToken(tokenRefreshRequest);
    }

    @PostMapping(value = API + V1 + "/user/delete/{phoneNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("phoneNumber") String phoneNumber) {
        service.deleteUserByPhoneNumber(phoneNumber);
    }
}
