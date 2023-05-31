package com.postgrado.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.postgrado.ecommerce.entity.Role;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String roleNane;
}
