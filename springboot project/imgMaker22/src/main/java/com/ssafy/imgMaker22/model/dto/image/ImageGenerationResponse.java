package com.ssafy.imgMaker22.model.dto.image;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
//요청에 대한 응답을 받을 DTO
public class ImageGenerationResponse {

    private long created;
    private String data;
}
