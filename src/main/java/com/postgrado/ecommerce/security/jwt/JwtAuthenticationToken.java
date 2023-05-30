package com.postgrado.ecommerce.security.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
//usa como base la clase UsernamePasswordAuthenticationToken
//filter
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final Object principal;

    public JwtAuthenticationToken(Object principal) {
        super(null);
        this.principal = principal;
        super.setAuthenticated(false);
    }

    public JwtAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities ) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true); // must use super, as we override
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
