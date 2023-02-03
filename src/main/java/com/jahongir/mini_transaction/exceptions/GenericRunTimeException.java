package com.jahongir.mini_transaction.exceptions;

import lombok.Getter;

/**
 * @author jahongir
 * @created 02/02/23 - 15:34
 * @project Mini_transaction/IntelliJ IDEA
 */
@Getter
public class GenericRunTimeException extends RuntimeException {
    protected final Integer statusCode;
    public GenericRunTimeException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
