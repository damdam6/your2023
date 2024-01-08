package com.ssafy.imgMaker22.model.service.prompt.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageUrlDto {
    private String url;

    public ImageUrlDto(String url){
        this.url = url;
    }

}
