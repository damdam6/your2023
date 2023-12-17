package com.ssafy.imgMaker22.model.dto.image;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class PromptRequest implements Serializable {
    private String prompt;
    @Builder
    public PromptRequest(String prompt) {
        this.prompt = prompt;
    }
}
