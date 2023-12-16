package com.ssafy.imgMaker22.model.dto.prompt;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageRequest {
    private String url;

    public ImageRequest(String url){
        this.url = url;
    }

}
