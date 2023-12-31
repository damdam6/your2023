package com.ssafy.imgMaker22.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
public class MakeImageRequest {

    private List<Image> images;
    private String nickname;
    private String style;

    @Builder
    public MakeImageRequest(List<Image> images, String nickname, String style) {
        this.images = images;
        this.nickname = nickname;
        this.style = style;
    }

    @Data
    public static class Image{
        private String url;
    }

}
