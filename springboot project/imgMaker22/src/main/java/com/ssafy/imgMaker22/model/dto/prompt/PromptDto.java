package com.ssafy.imgMaker22.model.dto.prompt;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PromptDto implements Comparable<PromptDto>{
    private String prompt;
    private double score;
    private String type;

    public PromptDto(String prompt, double score, String type) {
        this.prompt = prompt;
        this.score = score;
        this.type = type;
    }

    @Override
    public int compareTo(PromptDto o) {
        return (int) (o.score * 100000 - this.score * 100000);
    }
}
