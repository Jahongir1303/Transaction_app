package com.jahongir.mini_transaction.dtos.user;

import lombok.*;

/**
 * @author jahongir
 * @created 02/02/23 - 15:07
 * @project Mini_transaction/IntelliJ IDEA
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegisterRequest {
    private String phoneNumber;
    private String password;
    private String passwordConfirm;
}
