package com.ssafy.imgMaker22.model.service;

import com.ssafy.imgMaker22.model.dto.prompt.ImageRequest;
import com.ssafy.imgMaker22.model.dto.image.PromptRequest;

import java.util.List;

public interface CloudVisionService {

    public PromptRequest makePrompt(List<ImageRequest> imageRequests);
}
