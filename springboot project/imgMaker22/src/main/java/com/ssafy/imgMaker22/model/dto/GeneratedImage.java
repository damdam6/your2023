package com.ssafy.imgMaker22.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class GeneratedImage {
    private Long id;
    private String url;
    private String prompt;
    private String nickname;
    private String style;

    @Builder
    public GeneratedImage(String nickname, String style) {
        this.nickname = nickname;
        this.style = style;
    }


}
