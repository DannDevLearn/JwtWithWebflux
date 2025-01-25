package com.example.jjwt.webflux.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CatWebClientConfig {

    @Bean
    public WebClient catWebClient() {
        return WebClient.builder()
                .baseUrl("https://api.thecatapi.com")
                .build();
    }
}
