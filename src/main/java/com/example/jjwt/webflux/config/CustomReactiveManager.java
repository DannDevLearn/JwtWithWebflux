package com.example.jjwt.webflux.config;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CustomReactiveManager implements ReactiveAuthenticationManager {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public CustomReactiveManager(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        return customUserDetailsService.findByUsername(username)
                .filter(u -> passwordEncoder.matches(password, u.getPassword()))
                .switchIfEmpty(Mono.error(new BadCredentialsException("Bad credentials!")))
                .map(uDetails -> new UsernamePasswordAuthenticationToken(
                        uDetails,
                        uDetails.getPassword(),
                        uDetails.getAuthorities()
                ));
    }
}
