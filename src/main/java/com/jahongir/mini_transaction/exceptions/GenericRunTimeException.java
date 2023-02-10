package com.jahongir.mini_transaction.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author jahongir
 * @created 02/02/23 - 15:34
 * @project Mini_transaction/IntelliJ IDEA
 */
@Getter
public class GenericRunTimeException extends RuntimeException {
    protected final HttpStatus status;

    public GenericRunTimeException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
