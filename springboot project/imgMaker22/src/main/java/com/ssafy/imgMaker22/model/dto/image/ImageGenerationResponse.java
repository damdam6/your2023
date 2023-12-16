package com.ssafy.imgMaker22.model.dto.image;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
//요청에 대한 응답을 받을 DTO
public class ImageGenerationResponse {

    private long created;
    private List<ImageURL> data;

    @Getter
    @Setter
    public static class ImageURL {
        private String url;
    }
}
