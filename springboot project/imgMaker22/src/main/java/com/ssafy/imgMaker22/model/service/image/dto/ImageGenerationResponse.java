package com.ssafy.imgMaker22.model.service.image.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
//요청에 대한 응답을 받을 DTO
public class ImageGenerationResponse {

    private long created;
    private List<ResponseData> data;

    @Getter @Setter
    public static class ResponseData {
        @JsonProperty("b64_json")
        private String b64Json;
    }
}
