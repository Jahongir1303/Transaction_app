package com.jahongir.mini_transaction.exceptions;

/**
 * @author jahongir
 * @created 02/02/23 - 15:37
 * @project Mini_transaction/IntelliJ IDEA
 */

public class InvalidTokenException extends Exception {
    public InvalidTokenException() {
    }

    public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
