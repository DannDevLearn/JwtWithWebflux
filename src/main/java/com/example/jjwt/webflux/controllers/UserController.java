package com.example.jjwt.webflux.controllers;

import com.example.jjwt.webflux.models.dto.UserDto;
import com.example.jjwt.webflux.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Mono<Void>> registerNewUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.registerNewUser(userDto), HttpStatus.CREATED);
    }

}
