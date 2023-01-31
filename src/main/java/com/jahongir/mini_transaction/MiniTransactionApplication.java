package com.jahongir.mini_transaction;

import com.jahongir.mini_transaction.domains.User;
import com.jahongir.mini_transaction.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MiniTransactionApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiniTransactionApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User user = User.builder()
                    .password("7777")
                    .phoneNumber("+998946311303")
                    .build();
            userRepository.save(user);
        };
    }
}
