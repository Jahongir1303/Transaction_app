package com.jahongir.mini_transaction.service;

import com.jahongir.mini_transaction.domains.RefreshToken;
import com.jahongir.mini_transaction.domains.User;

import com.jahongir.mini_transaction.dtos.jwt.AccessTokenResponse;
import com.jahongir.mini_transaction.dtos.user.LoginRequest;
import com.jahongir.mini_transaction.dtos.user.RegisterRequest;
import com.jahongir.mini_transaction.enums.UserStatus;
import com.jahongir.mini_transaction.exceptions.GenericRunTimeException;

import com.jahongir.mini_transaction.mappers.UserMapper;
import com.jahongir.mini_transaction.repository.UserRepository;
import com.jahongir.mini_transaction.security.UserDetailsImpl;
import com.jahongir.mini_transaction.security.jwt.JwtUtils;
import com.jahongir.mini_transaction.service.base.AbstractService;
import com.jahongir.mini_transaction.service.base.GenericCreateService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author jahongir
 * @created 02/02/23 - 13:05
 * @project Mini_transaction/IntelliJ IDEA
 */
@Service
public class UserDetailsServiceImpl extends AbstractService<UserRepository, UserMapper> implements UserDetailsService, GenericCreateService<UUID, RegisterRequest> {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final RefreshTokenService refreshTokenService;

    public UserDetailsServiceImpl(UserRepository repository, UserMapper userMapper, @Lazy AuthenticationManager authenticationManager, JwtUtils jwtUtils, RefreshTokenService refreshTokenService) {
        super(repository, userMapper);
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = repository.findUserByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with phone number: " + phoneNumber));
        return new UserDetailsImpl(user);
    }

    public AccessTokenResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getPhoneNumber(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(principal);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(principal.getUser().getId());

        return new AccessTokenResponse(jwt,
                refreshToken.getToken(),
                principal.getUser().getId(),
                principal.getUsername()
        );
    }

    @Override
    public UUID create(RegisterRequest registerRequest) {
        if (repository.existsByPhoneNumber(registerRequest.getPhoneNumber())) {
            throw new GenericRunTimeException("Error: User has already exists with phone number: " + registerRequest.getPhoneNumber(), HttpStatus.BAD_REQUEST.value());
        }
        if (!registerRequest.getPassword().equals(registerRequest.getPasswordConfirm())) {
            throw new GenericRunTimeException("Error: Password are not the same" + registerRequest.getPhoneNumber(), HttpStatus.BAD_REQUEST.value());
        }

        User user = mapper.fromCreateDto(registerRequest);

        repository.save(user);

        return user.getId();
    }

    public String deleteUserByPhoneNumber(String phoneNumber) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        if (!principal.getUsername().equals(phoneNumber)) {
            throw new GenericRunTimeException("Wrong phone number!", HttpStatus.BAD_REQUEST.value());
        }
        User user = User.builder()
                .id(principal.getUser().getId())
                .phoneNumber(phoneNumber)
                .password(principal.getPassword())
                .status(UserStatus.DELETED)
                .build();

        repository.save(user);
        return "Successfully deleted";
    }
}
