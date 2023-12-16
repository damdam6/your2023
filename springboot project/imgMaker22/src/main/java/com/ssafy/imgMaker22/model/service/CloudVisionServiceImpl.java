package com.ssafy.imgMaker22.model.service;

import com.google.cloud.spring.vision.CloudVisionTemplate;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.ssafy.imgMaker22.model.dto.prompt.ImageRequest;
import com.ssafy.imgMaker22.model.dto.image.PromptDTO;
import com.ssafy.imgMaker22.model.dto.image.PromptRequest;
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
public class CloudVisionServiceImpl implements CloudVisionService {

    private static final int MAX_PROMPT_COUNT = 3;

    private ResourceLoader resourceLoader;

    private CloudVisionTemplate cloudVisionTemplate;

    @Override
    public PromptRequest makePrompt(List<ImageRequest> imageRequests) {

        List<PromptDTO> promptList = new ArrayList<>();

        promptList.add(new PromptDTO("Illustrate", 1));
        promptList.add(new PromptDTO("realistic", 1)); // 수정필요

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

        StringBuilder sb = new StringBuilder();
        for (PromptDTO p:promptList) {
            sb.append(p.getKeyword() + " ");
        }

        PromptRequest promptRequest = new PromptRequest(sb.toString().trim());
        return promptRequest;
    }

}
