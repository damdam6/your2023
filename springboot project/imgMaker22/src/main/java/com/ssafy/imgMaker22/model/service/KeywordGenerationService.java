package com.ssafy.imgMaker22.model.service;

import com.ssafy.imgMaker22.model.dto.prompt.PromptDTO;
import com.ssafy.imgMaker22.model.dto.prompt.ImageRequest;

import java.util.List;

public interface KeywordGenerationService {

    public List<PromptDTO> getKeywords(List<ImageRequest> imageRequests);
    public List<PromptDTO> getColors(List<ImageRequest> imageRequests);

}
