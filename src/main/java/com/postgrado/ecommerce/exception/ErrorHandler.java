package com.postgrado.ecommerce.exception;

import com.postgrado.ecommerce.exception.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(RoleAlreadyExist.class)
    public ResponseEntity<ErrorResponse> handleRoleAlreadyExistException(Exception ex) {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorResponse response = new ErrorResponse(status.value(), ex.toString(), ex.getMessage());
        return ResponseEntity.status(status).body(response);
    }
}
