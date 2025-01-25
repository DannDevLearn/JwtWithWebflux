package com.example.jjwt.webflux.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CatDto {

    private String id;
    private String url;
    private String width;
    private String height;

}
