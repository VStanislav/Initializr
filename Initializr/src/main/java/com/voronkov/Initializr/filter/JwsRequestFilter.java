package com.voronkov.Initializr.filter;

import com.voronkov.Initializr.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class JwsRequestFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authotization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authotization!=null && authotization.startsWith("Bearer ")){
            String bearerTokenValue = authotization.substring(7);

            String username = jwtService.getUsername(bearerTokenValue);
            List<GrantedAuthority> authorities = jwtService.getAuthority(bearerTokenValue);

            if (Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())){
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(username,null,authorities)
                );
            }
        }
        filterChain.doFilter(request,response);
    }
}
