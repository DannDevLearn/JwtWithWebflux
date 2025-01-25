package com.example.jjwt.webflux.repositories;


import com.example.jjwt.webflux.models.entities.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<UserEntity, Integer> {

    Mono<UserEntity> findByUsername(String username);

}
