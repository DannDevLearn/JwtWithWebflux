package com.example.jjwt.webflux.services;

import com.example.jjwt.webflux.models.dto.LoginDto;
import com.example.jjwt.webflux.models.dto.TokenDto;
import reactor.core.publisher.Mono;

public interface ILoginService {

    Mono<TokenDto> login(LoginDto loginDto);

}
