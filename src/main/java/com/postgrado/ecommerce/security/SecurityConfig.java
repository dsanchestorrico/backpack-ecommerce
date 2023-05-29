package com.postgrado.ecommerce.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
  private UserDetailService userDetailService;
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    http.csrf().disable(); //no autenticacion basica
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.authenticationProvider(authenticationProvider());
    http.authorizeHttpRequests()
        .requestMatchers("/register").permitAll()
        .anyRequest().authenticated();
    return http.build();
  }
  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
  @Bean
  public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }
}

