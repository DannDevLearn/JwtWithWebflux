package com.example.jjwt.webflux.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private String username;
    private String password;
    private String role;

}
