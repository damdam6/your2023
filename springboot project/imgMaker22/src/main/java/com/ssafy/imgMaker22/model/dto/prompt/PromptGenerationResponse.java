package com.ssafy.imgMaker22.model.dto.prompt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PromptGenerationResponse {

    private long created;
    private List<ResponseMessage> choices;

    public String getContent(){
        return this.choices.get(0).message.content;
    }

    @Getter
    @Setter
    public static class ResponseMessage {
        private Content message;
    }

    @Getter
    @Setter
    public static class Content {
        private String content;
    }


}
