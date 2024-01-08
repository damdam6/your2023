package com.ssafy.imgMaker22.model.service.prompt;

import com.ssafy.imgMaker22.model.service.prompt.dto.ImageUrlDto;

import java.util.List;

public interface PromptService {

    public String makePrompt(List<ImageUrlDto> imageURLS, String style);

}
