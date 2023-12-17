package com.ssafy.imgMaker22.model.service;

import com.google.cloud.spring.vision.CloudVisionTemplate;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.ssafy.imgMaker22.model.dto.image.PromptDTO;
import com.ssafy.imgMaker22.model.dto.prompt.ImageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class KeywordGenerationServiceCloudVision implements KeywordGenerationService {

    private static final int MAX_PROMPT_COUNT = 3;

    private ResourceLoader resourceLoader;

    private CloudVisionTemplate cloudVisionTemplate;

    @Override
    public List<PromptDTO> makePrompt(List<ImageRequest> imageRequests) {

        List<PromptDTO> promptList = new ArrayList<>();

        for (ImageRequest imageRequest : imageRequests) {
            Resource imageResource = resourceLoader.getResource(imageRequest.getUrl());
            AnnotateImageResponse res = this.cloudVisionTemplate.analyzeImage(imageResource,
                    Feature.Type.LABEL_DETECTION);

            for (EntityAnnotation e : res.getLabelAnnotationsList().stream().
                    sorted(((o1, o2) -> (int) ((o2.getScore() - o1.getScore()) * 10000))).
                    limit(MAX_PROMPT_COUNT).toList()) {
                promptList.add(new PromptDTO(e.getDescription(), e.getScore()));
            }

        }

        return promptList;
    }

}
