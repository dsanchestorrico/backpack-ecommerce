package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.UserDto;
import com.postgrado.ecommerce.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto getById(UUID id);
    List<User> getAll();
    User create(User user);
    boolean existEmail(String email);
}
