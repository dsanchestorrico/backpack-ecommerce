package com.postgrado.ecommerce.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationRequest {

    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String password;
    private String address;
}
