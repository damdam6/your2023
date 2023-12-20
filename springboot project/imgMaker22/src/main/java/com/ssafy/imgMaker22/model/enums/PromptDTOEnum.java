package com.ssafy.imgMaker22.model.enums;

import lombok.Getter;

@Getter
public enum PromptDTOEnum {
    KEYWORD("keyword"), COLOR("color");

    private String type;

    PromptDTOEnum(String type) {
        this.type = type;
    }

}
