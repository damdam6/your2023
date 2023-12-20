package com.ssafy.imgMaker22.model.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
public class GeneratedImage {

    @Id @GeneratedValue
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
