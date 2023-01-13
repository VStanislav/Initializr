package com.voronkov.Initializr.api;

import com.voronkov.Initializr.dto.AuthRequest;
import com.voronkov.Initializr.dto.AuthResponse;
import com.voronkov.Initializr.service.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;

    @PostMapping("/auth")
    public AuthResponse autorize(@RequestBody AuthRequest request){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        try {
            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            UserDetails user = (UserDetails) authenticate.getPrincipal();
            String jwtToken = jwtService.generateJwtToken(user);

            return new AuthResponse(jwtToken);
        }catch (AuthenticationException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,e.getMessage());
        }
    }
}
