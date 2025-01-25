package com.example.jjwt.webflux.services.impl;

import com.example.jjwt.webflux.models.dto.UserDto;
import com.example.jjwt.webflux.models.entities.UserEntity;
import com.example.jjwt.webflux.repositories.UserRepository;
import com.example.jjwt.webflux.services.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Mono<Void> registerNewUser(UserDto userDto) {
        return userRepository.save(UserEntity.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(userDto.getRole() == null ? "USER" : userDto.getRole())
                .build())
                .switchIfEmpty(Mono.error(new RuntimeException("User already exists")))
                .then();
    }

}
