package com.ssafy.imgMaker22.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.imgMaker22.model.dto.image.ImageGenerationResponse;
import com.ssafy.imgMaker22.model.dto.image.ImageGenerationResponseTest;
import com.ssafy.imgMaker22.model.dto.image.PromptRequest;

public interface ImageGenerationService {

    public ImageGenerationResponse makeImages(PromptRequest commentRequest);

    public ImageGenerationResponseTest makeImagesURLTEST(PromptRequest commentRequest) throws JsonProcessingException;

}
