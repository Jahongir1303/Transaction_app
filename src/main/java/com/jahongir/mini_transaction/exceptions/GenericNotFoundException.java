package com.jahongir.mini_transaction.exceptions;

/**
 * @author jahongir
 * @created 02/02/23 - 15:34
 * @project Mini_transaction/IntelliJ IDEA
 */

public class GenericNotFoundException extends GenericRunTimeException {
    public GenericNotFoundException(String message, Integer statusCode) {
        super(message, statusCode);
    }
}
