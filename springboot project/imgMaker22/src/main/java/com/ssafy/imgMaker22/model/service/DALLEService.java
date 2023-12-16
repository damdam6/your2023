package com.ssafy.imgMaker22.model.service;

import com.ssafy.imgMaker22.model.dto.image.ImageGenerationResponse;
import com.ssafy.imgMaker22.model.dto.image.PromptRequest;

public interface DALLEService {

    public ImageGenerationResponse makeImages(PromptRequest commentRequest);

}
