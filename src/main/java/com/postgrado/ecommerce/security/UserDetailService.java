package com.postgrado.ecommerce.security;

import com.postgrado.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {
  private UserService userService;
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userService.getByEmail(username);
  }
}
