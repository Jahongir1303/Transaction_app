package com.jahongir.mini_transaction.service;

import com.jahongir.mini_transaction.domains.RefreshToken;
import com.jahongir.mini_transaction.domains.User;
import com.jahongir.mini_transaction.dtos.user.LoginRequest;
import com.jahongir.mini_transaction.dtos.jwt.JwtResponse;
import com.jahongir.mini_transaction.dtos.user.RegisterRequest;
import com.jahongir.mini_transaction.exceptions.GenericRunTimeException;
import com.jahongir.mini_transaction.mappers.UserMapper;
import com.jahongir.mini_transaction.repository.UserRepository;
import com.jahongir.mini_transaction.security.UserDetailsImpl;
import com.jahongir.mini_transaction.security.jwt.JwtUtils;
import com.jahongir.mini_transaction.service.base.AbstractService;
import com.jahongir.mini_transaction.service.base.BaseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author jahongir
 * @created 02/02/23 - 13:05
 * @project Mini_transaction/IntelliJ IDEA
 */
@Service
public class UserDetailsServiceImpl extends AbstractService<UserRepository, UserMapper> implements UserDetailsService, BaseService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    RefreshTokenService refreshTokenService;

    public UserDetailsServiceImpl(UserRepository repository, UserMapper mapper) {
        super(repository, mapper);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = repository.findUserByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with phone number: " + phoneNumber));
        return UserDetailsImpl.build(user);
    }

    public JwtResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getPhoneNumber(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(principal);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(principal.getId());

        return new JwtResponse(jwt,
                refreshToken.getToken(),
                principal.getId(),
                principal.getUsername()
        );
    }

    public ResponseEntity<UUID> register(RegisterRequest registerRequest) {
        if (repository.existsByPhoneNumber(registerRequest.getPhoneNumber())) {
            throw new GenericRunTimeException("Error: User has already exists with phone number: " + registerRequest.getPhoneNumber(), HttpStatus.BAD_REQUEST.value());
        }
        if (!registerRequest.getPassword().equals(registerRequest.getPasswordConfirm())) {
            throw new GenericRunTimeException("Error: Password are not the same" + registerRequest.getPhoneNumber(), HttpStatus.BAD_REQUEST.value());
        }
        User user = User.builder()
                .phoneNumber(registerRequest.getPhoneNumber())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        repository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user.getId());
    }
}
