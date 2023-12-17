package com.ssafy.imgMaker22.model.service;

import com.ssafy.imgMaker22.model.dto.image.PromptDTO;
import com.ssafy.imgMaker22.model.dto.prompt.ImageRequest;

import java.util.List;

public interface KeywordGenerationService {

    public List<PromptDTO> makePrompt(List<ImageRequest> imageRequests);
}
