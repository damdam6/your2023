package com.ssafy.imgMaker22.model.service.keyword.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * GPT4에 이미지를 직접 넣을 수 있는 기능이 생기면서 키워드 기능 사용하지 않아 deprecated됨
 */
@Deprecated
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
