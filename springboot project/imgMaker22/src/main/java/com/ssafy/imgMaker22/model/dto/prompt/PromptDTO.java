package com.ssafy.imgMaker22.model.dto.prompt;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PromptDTO implements Comparable<PromptDTO>{
    private String prompt;
    private double score;
    private String type;

    public PromptDTO(String prompt, double score, String type) {
        this.prompt = prompt;
        this.score = score;
        this.type = type;
    }

    @Override
    public int compareTo(PromptDTO o) {
        return (int) (o.score * 100000 - this.score * 100000);
    }
}
