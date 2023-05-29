package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.RegistrationRequest;

public interface RegistrationService {
    String register(RegistrationRequest dto);
    String confirm(String token);
}
