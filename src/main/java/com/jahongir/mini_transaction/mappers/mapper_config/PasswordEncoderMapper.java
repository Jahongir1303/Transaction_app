package com.jahongir.mini_transaction.mappers.mapper_config;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author jahongir
 * @created 05/02/23 - 23:58
 * @project Mini_transaction/IntelliJ IDEA
 */
@Component
public class PasswordEncoderMapper {
    private final PasswordEncoder passwordEncoder;

    public PasswordEncoderMapper(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @EncodedMapping
    public String encode(String value) {
        return passwordEncoder.encode(value);
    }
}
