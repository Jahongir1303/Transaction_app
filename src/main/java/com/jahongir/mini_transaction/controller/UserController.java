package com.jahongir.mini_transaction.controller;

import com.jahongir.mini_transaction.dtos.user.LoginRequest;
import com.jahongir.mini_transaction.dtos.jwt.JwtResponse;
import com.jahongir.mini_transaction.dtos.user.RegisterRequest;
import com.jahongir.mini_transaction.service.UserDetailsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author jahongir
 * @created 02/02/23 - 14:37
 * @project Mini_transaction/IntelliJ IDEA
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController extends ApiController<UserDetailsServiceImpl> {
    public UserController(UserDetailsServiceImpl service) {
        super(service);
    }

    @PostMapping(value = API + V1 + "/user/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(service.login(loginRequest));
    }

    public ResponseEntity<UUID> register(@RequestBody RegisterRequest registerRequest) {
        return service.register(registerRequest);
    }
}
