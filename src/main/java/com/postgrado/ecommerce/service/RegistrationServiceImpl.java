package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.EmailNotification;
import com.postgrado.ecommerce.dto.RegistrationRequest;
import com.postgrado.ecommerce.entity.ConfirmationToken;
import com.postgrado.ecommerce.entity.User;
import com.postgrado.ecommerce.exception.EmailAlreadyTaken;
import com.postgrado.ecommerce.repository.ConfirmationTokenRepository;
import com.postgrado.ecommerce.util.HtmlGenerator;
import com.postgrado.ecommerce.util.UrlGenerator;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService{
    private UserService userService;
    private RoleService roleService;
    private ConfirmationTokenService confirmationTokenService;
    private PasswordEncoder passwordEncoder;
    private EmailService emailService;

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
        user.setEmail(dto.getEmail());
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        user.setPassword(encodedPassword);
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
        String confirmationLink = UrlGenerator.create("/auth/confirm","token",token);
        String bodyHtml = HtmlGenerator.generateConfirmationTemplate(user.getFirstName(),confirmationLink);
        EmailNotification emailNotification = EmailNotification.builder()
                .hasTemplate(true)
                .to(user.getEmail())
                .subject("Confirmation Account")
                .body(bodyHtml)
                .build();

        //emailService.send(emailNotification);
        return token;
    }

    @Override
    public String confirm(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getByToken(token);
        if(confirmationToken.getConfirmedAt()!= null){
            throw new RuntimeException("token already confirmed");
        }
        if(confirmationToken.getExpireAt().isBefore(LocalDateTime.now())){
            throw new RuntimeException("token expired");
        }
        userService.enableUser(confirmationToken.getUser());
        confirmationTokenService.setConfirmedAt(confirmationToken);
        return "User account has been enabled successfully";
    }
}
