package com.ssafy.imgMaker22.model.service;

import com.google.cloud.spring.vision.CloudVisionTemplate;
import com.google.cloud.vision.v1.*;
import com.ssafy.imgMaker22.model.dto.prompt.ImageRequest;
import com.ssafy.imgMaker22.model.dto.prompt.PromptDTO;
import com.ssafy.imgMaker22.model.enums.PromptDTOEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class KeywordGenerationServiceCloudVision implements KeywordGenerationService {

    private static final int MAX_KEYWORD_COUNT = 2;
    private static final int MAX_COLOR_COUNT = 1;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private CloudVisionTemplate cloudVisionTemplate;

    @Override
    public List<PromptDTO> getKeywords(List<ImageRequest> imageRequests) {

        List<PromptDTO> promptList = new ArrayList<>();

        for (ImageRequest imageRequest : imageRequests) {
            Resource imageResource = resourceLoader.getResource(imageRequest.getUrl());
            AnnotateImageResponse res = this.cloudVisionTemplate.analyzeImage(imageResource,
                    Feature.Type.LABEL_DETECTION);

            for (EntityAnnotation e : res.getLabelAnnotationsList().stream().
                    sorted(((o1, o2) -> (int) ((o2.getScore() - o1.getScore()) * 10000))).
                    limit(MAX_KEYWORD_COUNT).toList()) {
                promptList.add(new PromptDTO(e.getDescription(), e.getScore(), PromptDTOEnum.KEYWORD.getType()));
            }

        }

        return promptList;
    }

    public List<PromptDTO> getColors(List<ImageRequest> imageRequests){

        List<PromptDTO> promptList = new ArrayList<>();

        for (ImageRequest imageRequest : imageRequests) {
            Resource imageResource = resourceLoader.getResource(imageRequest.getUrl());
            AnnotateImageResponse res = this.cloudVisionTemplate.analyzeImage(imageResource,
                    Feature.Type.IMAGE_PROPERTIES);

            if (res.hasError()) {
                System.out.format("Error: %s%n", res.getError().getMessage());
                return null;
            }

            // For full list of available annotations, see http://g.co/cloud/vision/docs
            DominantColorsAnnotation colors = res.getImagePropertiesAnnotation().getDominantColors();
            for (ColorInfo color : colors.getColorsList().stream().
                    sorted(((o1, o2) -> (int) ((o2.getScore() - o1.getScore()) * 10000))).
                    limit(MAX_COLOR_COUNT).toList()) {
                StringBuilder sb = new StringBuilder();
                sb.append("RGB(").
                        append((int)color.getColor().getRed() + ", ").
                        append((int)color.getColor().getGreen() + ", ").
                        append((int)color.getColor().getBlue() + ")");
                promptList.add(new PromptDTO(sb.toString(), 0, PromptDTOEnum.COLOR.getType()));
            }
        }

        return promptList;
    }



}
