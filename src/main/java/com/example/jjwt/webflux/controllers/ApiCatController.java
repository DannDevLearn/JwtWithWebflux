package com.example.jjwt.webflux.controllers;

import com.example.jjwt.webflux.models.dto.CatDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/cats")
public class ApiCatController {

    private final WebClient webClient;

    public ApiCatController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping
    public Flux<CatDto> getCats(){
        return webClient.get()
                .uri("/v1/images/search?limit=10")
                .retrieve()
                .bodyToFlux(CatDto.class);
    }

}
