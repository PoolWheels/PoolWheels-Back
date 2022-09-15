package com.UNIWheels.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TokenAuthentication extends AbstractAuthenticationToken {

    String token;
    String subject;
    List<String> roles;

    public TokenAuthentication( String token, String subject, List<String> roles ) {
        super( null );
        this.token = token;
        this.subject = subject;
        this.roles = roles;
    }

    /**
     * If the token, subject, and roles are not empty, then the user is authenticated
     * @return A boolean value.
     */
    @Override
    public boolean isAuthenticated() {
        return !token.isEmpty() && !subject.isEmpty() && !roles.isEmpty();
    }

    /**
     *  The function returns the token that was used to authenticate the user.
     * @return The token
     */
    @Override
    public Object getCredentials() {
        return token;
    }

    /**
     * Returns the principal to identify the currently authenticated user.
     * @return The subject.
     */
    @Override
    public Object getPrincipal() {
        return subject;
    }

    /**
     * For each role in the roles collection, create a SimpleGrantedAuthority object with the role name prefixed with
     * "ROLE_" and return a collection of all of these objects.
     * @return A collection of GrantedAuthority objects.
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return roles.stream().map( role -> new SimpleGrantedAuthority( "ROLE_" + role ) ).collect(
                Collectors.toList() );
    }

}