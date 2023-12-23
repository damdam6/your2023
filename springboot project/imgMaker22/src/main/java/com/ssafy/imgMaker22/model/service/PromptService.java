package com.ssafy.imgMaker22.model.service;

import com.ssafy.imgMaker22.model.dto.GeneratedImage;
import com.ssafy.imgMaker22.model.dto.prompt.ImageRequest;

import java.util.List;

public interface PromptService {

    public String makePrompt(List<ImageRequest> imageURLS, String style);

    public String makePromptTest(List<ImageRequest> imageURLS, String style);
}
