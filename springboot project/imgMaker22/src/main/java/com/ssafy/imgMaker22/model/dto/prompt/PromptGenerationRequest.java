package com.ssafy.imgMaker22.model.dto.prompt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PromptGenerationRequest {

    private String model;
    private List<String> messages;

    @Builder
    public PromptGenerationRequest(String model, List<String> messages) {
        this.model = model;
        this.messages = messages;
    }
}
