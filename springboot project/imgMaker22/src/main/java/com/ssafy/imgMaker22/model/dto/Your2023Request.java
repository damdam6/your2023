package com.ssafy.imgMaker22.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class Your2023Request {

    private List<byte[]> images;
    private String nickname;
    private String style;

    @Builder

    public Your2023Request(List<byte[]> images, String nickname, String style) {
        this.images = images;
        this.nickname = nickname;
        this.style = style;
    }
}
