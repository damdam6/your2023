package com.ssafy.imgMaker22.model.service;

import com.ssafy.imgMaker22.model.dto.prompt.PromptDto;
import com.ssafy.imgMaker22.model.dto.prompt.ImageRequest;
import reactor.core.publisher.Flux;

import java.util.List;

public interface KeywordGenerationService {

    public Flux<PromptDto> getKeywords(List<ImageRequest> imageRequests);
    public Flux<PromptDto> getColors(List<ImageRequest> imageRequests);

}
