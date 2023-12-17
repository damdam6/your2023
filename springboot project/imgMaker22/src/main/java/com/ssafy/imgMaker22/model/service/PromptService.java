package com.ssafy.imgMaker22.model.service;

import com.ssafy.imgMaker22.model.dto.GeneratedImage;
import com.ssafy.imgMaker22.model.dto.image.PromptDTO;

import java.util.List;

public interface PromptService {

    public String makePrompt(GeneratedImage gImage, List<PromptDTO> promptDTOs, String style);

}
