package com.example.jjwt.webflux.controllers;

import com.example.jjwt.webflux.models.dto.LoginDto;
import com.example.jjwt.webflux.models.dto.TokenDto;
import com.example.jjwt.webflux.services.ILoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final ILoginService loginService;

    public LoginController(ILoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<Mono<TokenDto>> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(loginService.login(loginDto));
    }

}
