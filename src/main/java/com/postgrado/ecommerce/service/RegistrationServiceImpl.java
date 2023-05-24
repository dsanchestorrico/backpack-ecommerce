package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.RegistrationRequest;
import com.postgrado.ecommerce.entity.ConfirmationToken;
import com.postgrado.ecommerce.entity.User;
import com.postgrado.ecommerce.exception.EmailAlreadyTaken;
import com.postgrado.ecommerce.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService{
    private UserService userService;
    private RoleService roleService;
    private ConfirmationTokenService confirmationTokenService;
    @Override
    public String register(RegistrationRequest dto) {
        boolean existUser = userService.existEmail(dto.getEmail());
        if(existUser){
            throw new EmailAlreadyTaken("Email already taken");
        }

        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setAddress(dto.getAddress());
        //TODO: password encryptation
        user.setPassword(dto.getPassword());
        user.setRole(roleService.getByName("USER"));

        userService.create(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        confirmationTokenService.create(confirmationToken);

        //TODO: Send Token by email
        return token;
    }
}
