package com.jahongir.mini_transaction.handler;

import com.jahongir.mini_transaction.dtos.response.InternalErrorResponse;
import com.jahongir.mini_transaction.dtos.response.ValidationErrorResponse;
import com.jahongir.mini_transaction.exceptions.GenericNotFoundException;
import com.jahongir.mini_transaction.exceptions.GenericRunTimeException;
import com.jahongir.mini_transaction.exceptions.InvalidTokenException;
import com.jahongir.mini_transaction.exceptions.TokenRefreshException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jahongir
 * @created 10/02/23 - 12:11
 * @project Mini_transaction/IntelliJ IDEA
 */
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = GenericNotFoundException.class)
    @ResponseBody
    public ResponseEntity<InternalErrorResponse> handle404(GenericNotFoundException e, HttpServletRequest request) {
        return new ResponseEntity<>(InternalErrorResponse.builder()
                .developerMessage(Arrays.toString(e.getStackTrace()))
                .friendlyMessage(e.getMessage())
                .requestPath(request.getRequestURL().toString())
                .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .build(), e.getStatus());
    }

    @ExceptionHandler(value = GenericRunTimeException.class)
    @ResponseBody
    public ResponseEntity<InternalErrorResponse> handleRuntimeException(GenericRunTimeException e, HttpServletRequest request) {
        return new ResponseEntity<>(InternalErrorResponse.builder()
                .developerMessage(Arrays.toString(e.getStackTrace()))
                .friendlyMessage(e.getMessage())
                .requestPath(request.getRequestURL().toString())
                .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .build(), e.getStatus());
    }

    @ExceptionHandler(value = TokenRefreshException.class)
    @ResponseBody
    public ResponseEntity<InternalErrorResponse> handleTokenRefreshException(TokenRefreshException ex, HttpServletRequest request) {
        return new ResponseEntity<>(InternalErrorResponse.builder()
                .developerMessage(Arrays.toString(ex.getStackTrace()))
                .friendlyMessage(ex.getMessage())
                .requestPath(request.getRequestURL().toString())
                .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .build(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = InvalidTokenException.class)
    @ResponseBody
    public ResponseEntity<InternalErrorResponse> handleInvalidTokenException(InvalidTokenException ex, HttpServletRequest request) {
        return new ResponseEntity<>(InternalErrorResponse.builder()
                .developerMessage(Arrays.toString((ex.getStackTrace())))
                .friendlyMessage(ex.getMessage())
                .requestPath(request.getRequestURL().toString())
                .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    @ResponseBody
    public ResponseEntity<InternalErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex, HttpServletRequest request) {
        return new ResponseEntity<>(InternalErrorResponse.builder()
                .developerMessage(Arrays.toString((ex.getStackTrace())))
                .friendlyMessage(ex.getMessage())
                .requestPath(request.getRequestURL().toString())
                .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .build(), HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ValidationErrorResponse validationErrorResponse =
                new ValidationErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(
                ex, validationErrorResponse, headers, validationErrorResponse.getStatus(), request);

    }
}
