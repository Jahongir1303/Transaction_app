package com.jahongir.mini_transaction.dtos.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

/**
 * @author jahongir
 * @created 10/02/23 - 12:48
 * @project Mini_transaction/IntelliJ IDEA
 */
@Getter
@Setter
public class ValidationErrorResponse {
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ValidationErrorResponse(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ValidationErrorResponse(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
}
