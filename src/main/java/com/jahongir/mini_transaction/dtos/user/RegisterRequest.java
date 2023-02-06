package com.jahongir.mini_transaction.dtos.user;

import com.jahongir.mini_transaction.dtos.base.Dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

/**
 * @author jahongir
 * @created 02/02/23 - 15:07
 * @project Mini_transaction/IntelliJ IDEA
 */

@Getter
@Setter
public class RegisterRequest implements Dto {
    @NotBlank(message = "Phone number can not be null or empty")
    @Pattern(regexp = "^[+]998[395][01345789][0-9]{7}$")
    private String phoneNumber;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
            message = "The password must contain at least one lowercase character," +
                    " one uppercase character, one digit, one special character," +
                    " and a length between 8 to 20.")
    private String password;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
            message = "The password must contain at least one lowercase character," +
                    " one uppercase character, one digit, one special character," +
                    " and a length between 8 to 20.")
    private String passwordConfirm;

}
