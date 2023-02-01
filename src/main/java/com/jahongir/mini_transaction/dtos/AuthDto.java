package com.jahongir.mini_transaction.dtos;

import com.jahongir.mini_transaction.dtos.base.Dto;
import lombok.*;

/**
 * @author jahongir
 * @created 02/02/23 - 01:02
 * @project Mini_transaction/IntelliJ IDEA
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuthDto implements Dto {
    private String phoneNumber;
    private String password;
}
