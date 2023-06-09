package com.postgrado.ecommerce.exception.response;

import lombok.*;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private int code;
    private String error;
    private String message;
}
