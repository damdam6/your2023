package com.ssafy.imgMaker22.model.dto.image;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
//OpenAI에 요청할 DTO Format('response_format' 추가하셔도됩니다.)
public class ImageGenerationRequest implements Serializable {
    private String model;
    private String prompt;
    private int n;
    private String size;

    @Builder
    public ImageGenerationRequest(String prompt, int n, String size, String model) {
        this.prompt = prompt;
        this.n = n;
        this.size = size;
        this.model = model;
    }
}
