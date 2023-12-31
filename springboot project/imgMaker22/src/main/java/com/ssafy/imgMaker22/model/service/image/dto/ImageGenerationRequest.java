package com.ssafy.imgMaker22.model.service.image.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class ImageGenerationRequest implements Serializable {
    private String model;
    private String prompt;
    private String response_format;
    private int n;
    private String size;

    @Builder
    public ImageGenerationRequest(String model, String prompt, String response_format, int n, String size) {
        this.model = model;
        this.prompt = prompt;
        this.response_format = response_format;
        this.n = n;
        this.size = size;
    }
}
