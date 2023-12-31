package com.ssafy.imgMaker22.model.service.keyword;

import com.ssafy.imgMaker22.model.service.keyword.dto.PromptDto;
import com.ssafy.imgMaker22.model.service.prompt.dto.ImageUrlDto;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * GPT4에 이미지를 직접 넣을 수 있는 기능이 생기면서 키워드 기능 사용하지 않아 deprecated됨
 */
@Deprecated
public interface KeywordGenerationService {

    public Flux<PromptDto> getKeywords(List<ImageUrlDto> imageRequests);
    public Flux<PromptDto> getColors(List<ImageUrlDto> imageRequests);

}
