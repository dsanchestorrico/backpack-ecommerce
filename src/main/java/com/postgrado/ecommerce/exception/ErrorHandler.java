package com.postgrado.ecommerce.exception;

import com.postgrado.ecommerce.exception.response.CustomErrorResponse;
import com.postgrado.ecommerce.exception.response.ErrorResponse;
import com.postgrado.ecommerce.exception.response.FieldErrorModel;
import com.postgrado.ecommerce.exception.response.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(Exception ex){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse response = ErrorResponse.builder()
                .code(status.value())
                .error(status.name())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(status).body(response);
    }
    @ExceptionHandler(EmailAlreadyTaken.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyTakenException(Exception ex){
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorResponse response = ErrorResponse.builder()
                .code(status.value())
                .error(status.name())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(status).body(response);
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(Exception ex){
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ErrorResponse response = ErrorResponse.builder()
                .code(status.value())
                .error(status.name())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(status).body(response);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<FieldErrorModel> errors = ex.getBindingResult().getAllErrors().stream().map(fieldError -> {
            FieldErrorModel fieldErrorModel = new FieldErrorModel();
            fieldErrorModel.setCode(fieldError.getCode());
            fieldErrorModel.setMessage(fieldError.getDefaultMessage());
            fieldErrorModel.setField(((FieldError)fieldError).getField());
            return fieldErrorModel;
        }).toList();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ValidationErrorResponse response = new ValidationErrorResponse();
        response.setCode(status.value());
        response.setMessage(errors);
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(RoleAlreadyExist.class)
    public ResponseEntity<CustomErrorResponse> handleRoleAlreadyExistException(Exception ex) {
        HttpStatus status = HttpStatus.CONFLICT;
        CustomErrorResponse response = CustomErrorResponse.builder()
                .status(status.value())
                .error(ex.toString())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(status).body(response);
    }
}
