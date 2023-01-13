package com.voronkov.Initializr.service;


import com.voronkov.Initializr.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JWTService {

       @Autowired
       private JwtProperties jwtProperties;

    public String generateJwtToken(UserDetails userDetails){
        String username = userDetails.getUsername();
        List<String> authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        Map<String,Object> claims = new HashMap<>(Map.of("authority",authorities));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpireTime()))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .compact();
    }

    public String getUsername(String bearerTokenValue) {
        return parse(bearerTokenValue).getSubject();
    }

    public List<GrantedAuthority> getAuthority(String bearertokenvalue) {
        List<String> authority = (List<String>) parse(bearertokenvalue).get("authority");
        return authority.stream()
                .map(SimpleGrantedAuthority::new)
                .map(it ->(GrantedAuthority) it)
                .toList();
    }

    private Claims parse(String value){
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(value)
                .getBody();
    }
}
