package com.postgrado.ecommerce.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.postgrado.ecommerce.entity.User;
import com.postgrado.ecommerce.security.UserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Service
public class JwtServiceImpl implements JwtService {
    public static final String SECRET_KEY = "s3cr3tk3y";
    private UserDetailService userDetailService;
    @Override
    public String createToken(User user) {
        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("role", user.getRole().getName())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+ TimeUnit.MINUTES.toMillis(30)))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    @Override
    public User decodeToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String email = decodedJWT.getSubject();
        return (User)userDetailService.loadUserByUsername(email);
    }
}
