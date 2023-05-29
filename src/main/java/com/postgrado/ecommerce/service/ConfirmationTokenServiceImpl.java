package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.entity.ConfirmationToken;
import com.postgrado.ecommerce.exception.EntityNotFoundException;
import com.postgrado.ecommerce.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService{

    private ConfirmationTokenRepository confirmationTokenRepository;
    @Override
    public ConfirmationToken create(ConfirmationToken confirmationToken) {
        return confirmationTokenRepository.save(confirmationToken);
    }

    @Override
    public ConfirmationToken getByToken(String token) {
        return confirmationTokenRepository.findByToken(token).orElseThrow(()->new EntityNotFoundException("Confirmation token not found"));
    }

    @Override
    public void setConfirmedAt(ConfirmationToken confirmationToken) {
        confirmationToken.setConfirmedAt(LocalDateTime.now());
        confirmationTokenRepository.save(confirmationToken);
    }
}
