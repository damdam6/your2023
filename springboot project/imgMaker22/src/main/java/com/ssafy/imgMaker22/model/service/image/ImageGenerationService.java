package com.ssafy.imgMaker22.model.service.image;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.imgMaker22.model.service.image.dto.ImageGenerationResponse;
import com.ssafy.imgMaker22.model.service.image.dto.ImageGenerationResponseTest;

public interface ImageGenerationService {

    public ImageGenerationResponse makeImages(String generatedPrompt);

    public ImageGenerationResponseTest makeImagesURLTEST(String generatedPrompt) throws JsonProcessingException;

}
