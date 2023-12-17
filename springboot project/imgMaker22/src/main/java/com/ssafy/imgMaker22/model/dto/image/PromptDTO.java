package com.ssafy.imgMaker22.model.dto.image;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PromptDTO implements Comparable<PromptDTO>{
    private String keyword;
    private double score;

    public PromptDTO(String keyword, double score) {
        this.keyword = keyword;
        this.score = score;
    }

    @Override
    public int compareTo(PromptDTO o) {
        return (int) (o.score * 100000 - this.score * 100000);
    }
}
