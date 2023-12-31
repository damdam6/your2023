package com.ssafy.imgMaker22.model.service.image;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.imgMaker22.config.DallE3Config;
import com.ssafy.imgMaker22.model.service.image.dto.ImageGenerationRequest;
import com.ssafy.imgMaker22.model.service.image.dto.ImageGenerationResponse;
import com.ssafy.imgMaker22.model.service.image.dto.ImageGenerationResponseTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Slf4j
@RequiredArgsConstructor
@Service
public class ImageGenerationServiceDALLE3 implements ImageGenerationService {

    private final RestTemplate restTemplate;

    @Value("${openai.dalle.api}")
    private String apiKey;

    @Override
    public ImageGenerationResponse makeImages(String generatedPrompt){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(DallE3Config.MEDIA_TYPE));
        httpHeaders.add(DallE3Config.AUTHORIZATION, DallE3Config.BEARER + apiKey);

        ImageGenerationRequest imageGenerationRequest = ImageGenerationRequest.builder()
                .model(DallE3Config.MODEL)
                .prompt(generatedPrompt)
                .response_format(DallE3Config.RESPONSE_FORMAT)
                .n(DallE3Config.IMAGE_COUNT)
                .size(DallE3Config.IMAGE_SIZE)
                .build();

        HttpEntity<ImageGenerationRequest> requestHttpEntity = new HttpEntity<>(imageGenerationRequest, httpHeaders);

        ResponseEntity<ImageGenerationResponse> responseEntity = restTemplate.postForEntity(
                DallE3Config.IMAGE_URL,
                requestHttpEntity,
                ImageGenerationResponse.class
        );

        return responseEntity.getBody();
    }

    @Override
    public ImageGenerationResponseTest makeImagesURLTEST(String generatedPrompt) throws JsonProcessingException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(DallE3Config.MEDIA_TYPE));
        httpHeaders.add(DallE3Config.AUTHORIZATION, DallE3Config.BEARER + apiKey);

        ImageGenerationRequest imageGenerationRequest = ImageGenerationRequest.builder()
                .model(DallE3Config.MODEL)
                .prompt(generatedPrompt)
                .n(DallE3Config.IMAGE_COUNT)
                .response_format(DallE3Config.RESPONSE_FORMAT_URL)
                .size(DallE3Config.IMAGE_SIZE)
                .build();

        HttpEntity<ImageGenerationRequest> requestHttpEntity = new HttpEntity<>(imageGenerationRequest, httpHeaders);

        ResponseEntity<ImageGenerationResponseTest> responseEntity = restTemplate.postForEntity(
                DallE3Config.IMAGE_URL,
                requestHttpEntity,
                ImageGenerationResponseTest.class
        );


        return responseEntity.getBody();
    }
}