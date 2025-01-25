package com.example.jjwt.webflux.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/hello")
public class HelloPublicController {

    @GetMapping
    public Mono<String> sayHello(){
        return Mono.just(("Hello world!"));
    }
}
