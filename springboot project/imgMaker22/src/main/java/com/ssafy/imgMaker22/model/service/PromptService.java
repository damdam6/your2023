package com.ssafy.imgMaker22.model.service;

import com.ssafy.imgMaker22.model.dto.GeneratedImage;
import com.ssafy.imgMaker22.model.dto.prompt.PromptDTO;

import java.util.List;
import java.util.Map;

public interface PromptService {

    public String makePrompt(GeneratedImage gImage, Map<String, List<PromptDTO>> promptDTOMap, String style);

}
