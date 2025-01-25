package com.example.jjwt.webflux.models.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("users")
public class UserEntity {

    @Id
    private Integer id;
    private String username;
    private String password;
    private String role;

}
