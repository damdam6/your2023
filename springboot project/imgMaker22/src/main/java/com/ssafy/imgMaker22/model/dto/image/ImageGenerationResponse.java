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
    private List<ResponseData> data;

    @Getter @Setter
    public static class ResponseData {
        private String b64_json;
    }
}
