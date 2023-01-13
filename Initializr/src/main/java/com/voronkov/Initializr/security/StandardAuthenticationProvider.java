package com.voronkov.Initializr.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class StandardAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    protected final UserDetailsService userDetailsService;

    @Override
    //проверка пароля
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (!Objects.equals(userDetails.getPassword(),authentication.getCredentials())){
            log.error("Bad cr for {}",userDetails.getUsername());
            throw new BadCredentialsException("Bad cred");
        }
    }

    @Override
    //проверка пользователя
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        return userDetailsService.loadUserByUsername(username);
    }
}
