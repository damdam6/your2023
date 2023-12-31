package com.ssafy.imgMaker22.model.service.keyword;

import com.google.cloud.spring.vision.CloudVisionTemplate;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.ColorInfo;
import com.google.cloud.vision.v1.DominantColorsAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.ssafy.imgMaker22.model.service.keyword.dto.PromptDto;
import com.ssafy.imgMaker22.model.service.prompt.dto.ImageUrlDto;
import com.ssafy.imgMaker22.model.enums.PromptDTOEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * GPT4에 이미지를 직접 넣을 수 있는 기능이 생기면서 키워드 기능 사용하지 않아 deprecated됨
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Deprecated
public class KeywordGenerationServiceCloudVision implements KeywordGenerationService {

    private static final int MAX_KEYWORD_COUNT = 2;
    private static final int MAX_COLOR_COUNT = 1;

    @Autowired
    private final ResourceLoader resourceLoader;

    @Autowired
    private final CloudVisionTemplate cloudVisionTemplate;

    @Override
    public Flux<PromptDto> getKeywords(List<ImageUrlDto> imageRequests) {
        return Flux.fromIterable(imageRequests)
                .flatMap(imageRequest -> {
                    Resource imageResource = resourceLoader.getResource(imageRequest.getUrl());

                    // cloudVisionTemplate를 사용하여 동기적으로 이미지 분석 수행
                    AnnotateImageResponse res = cloudVisionTemplate.analyzeImage(imageResource,
                            Feature.Type.LABEL_DETECTION);


                    return Flux.fromIterable(res.getLabelAnnotationsList())
                            .sort((o1, o2) -> (int) ((o2.getScore() - o1.getScore()) * 10000))
                            .take(MAX_KEYWORD_COUNT)
                            .map(e -> new PromptDto(e.getDescription(), e.getScore(), PromptDTOEnum.KEYWORD.getType()));
                });
    }



    public Flux<PromptDto> getColors(List<ImageUrlDto> imageRequests){

        return Flux.fromIterable(imageRequests)
                .flatMap(imageRequest -> {
                    Resource imageResource = resourceLoader.getResource(imageRequest.getUrl());

                    // cloudVisionTemplate를 사용하여 동기적으로 이미지 분석 수행
                    AnnotateImageResponse res = cloudVisionTemplate.analyzeImage(imageResource,
                            Feature.Type.LABEL_DETECTION);
                    DominantColorsAnnotation colors = res.getImagePropertiesAnnotation().getDominantColors();

                    return Flux.fromIterable(colors.getColorsList())
                            .sort((o1, o2) -> (int) ((o2.getScore() - o1.getScore()) * 10000))
                            .take(MAX_COLOR_COUNT)
                            .map(KeywordGenerationServiceCloudVision::makeRGB);
                });
    }

    static PromptDto makeRGB(ColorInfo color){
        StringBuilder sb = new StringBuilder();
        sb.append("RGB(").
                append((int)color.getColor().getRed() + ", ").
                append((int)color.getColor().getGreen() + ", ").
                append((int)color.getColor().getBlue() + ")");
        return new PromptDto(sb.toString(), 0, PromptDTOEnum.COLOR.getType());
    }



}
