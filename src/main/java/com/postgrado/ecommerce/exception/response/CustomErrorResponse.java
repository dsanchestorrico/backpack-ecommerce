package com.postgrado.ecommerce.exception.response;

import lombok.*;

@Getter
@Setter
@Builder
public class CustomErrorResponse {
    private int status;
    private String error;
    private String message;
}
