package com.example.jjwt.webflux.services.impl;

import com.example.jjwt.webflux.config.CustomReactiveManager;
import com.example.jjwt.webflux.config.security.JwtUtil;
import com.example.jjwt.webflux.models.dto.LoginDto;
import com.example.jjwt.webflux.models.dto.TokenDto;
import com.example.jjwt.webflux.services.ILoginService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements ILoginService {

    private final JwtUtil jwtUtil;
    private final CustomReactiveManager customReactiveManager;

    public LoginServiceImpl(JwtUtil jwtUtil, CustomReactiveManager customReactiveManager) {
        this.jwtUtil = jwtUtil;
        this.customReactiveManager = customReactiveManager;
    }

    // TODO Implement the login method
    @Override
    public Mono<TokenDto> login(LoginDto loginDto) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        );

        return customReactiveManager.authenticate(authentication)
                .map(auth -> {
                    Map<String, Object> claims = new HashMap<>();
                    auth.getAuthorities().forEach(a -> claims.put("role", a.getAuthority()));
                    return new TokenDto(jwtUtil.generateToken(auth.getName(), claims));
                });
    }
}
