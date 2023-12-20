package com.ssafy.imgMaker22.model.service;

import com.ssafy.imgMaker22.model.dto.prompt.PromptDTO;
import com.ssafy.imgMaker22.model.dto.prompt.ImageRequest;
import reactor.core.publisher.Flux;

import java.util.List;

public interface KeywordGenerationService {

    public Flux<PromptDTO> getKeywords(List<ImageRequest> imageRequests);
    public Flux<PromptDTO> getColors(List<ImageRequest> imageRequests);

}
