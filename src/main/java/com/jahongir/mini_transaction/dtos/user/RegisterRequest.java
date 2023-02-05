package com.jahongir.mini_transaction.dtos.user;

import com.jahongir.mini_transaction.dtos.base.Dto;
import lombok.*;

/**
 * @author jahongir
 * @created 02/02/23 - 15:07
 * @project Mini_transaction/IntelliJ IDEA
 */

@Getter
@Setter
public class RegisterRequest implements Dto {
    private String phoneNumber;
    private String password;
    private String passwordConfirm;

}
