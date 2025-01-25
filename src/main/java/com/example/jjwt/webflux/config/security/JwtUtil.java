package com.example.jjwt.webflux.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class JwtUtil {


    @Value("${jwt.secret.key}")
    private  String SECRET_KEY;

    @Value("${jwt.time.expiration}")
    private String EXPIRATION_TIME;

    public String generateToken(String username, Map<String, Object> claims){
        return Jwts.builder()
                .subject(username)
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date((System.currentTimeMillis() + Long.parseLong(EXPIRATION_TIME))))
                .signWith(getSignatureKey())
                .compact();
    }

    private Key getSignatureKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }

}
