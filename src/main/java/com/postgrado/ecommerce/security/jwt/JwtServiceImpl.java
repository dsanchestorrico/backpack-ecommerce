package com.postgrado.ecommerce.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.postgrado.ecommerce.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Service
public class JwtServiceImpl implements JwtService {
    public static final String SECRET_KEY = "s3cr3tk3y";
    @Override
    public String createToken(User user) {
        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("role", user.getRole().getName())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+ TimeUnit.MINUTES.toMillis(30)))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }
}
