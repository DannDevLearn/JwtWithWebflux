package com.example.jjwt.webflux.services;

import com.example.jjwt.webflux.models.dto.UserDto;
import reactor.core.publisher.Mono;

public interface IUserService {

    Mono<Void> registerNewUser(UserDto userDto);

}
