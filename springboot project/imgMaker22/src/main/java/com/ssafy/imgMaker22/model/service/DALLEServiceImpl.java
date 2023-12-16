package com.ssafy.imgMaker22.model.service;

import com.ssafy.imgMaker22.config.ChatGptConfig;
import com.ssafy.imgMaker22.model.dto.image.ImageGenerationRequest;
import com.ssafy.imgMaker22.model.dto.image.ImageGenerationResponse;
import com.ssafy.imgMaker22.model.dto.image.PromptRequest;
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
public class DALLEServiceImpl implements DALLEService{

    private final RestTemplate restTemplate;

    @Value("${api}")
    private String apiKey;

    public ImageGenerationResponse makeImages(PromptRequest commentRequest){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        httpHeaders.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + apiKey);

        ImageGenerationRequest imageGenerationRequest = ImageGenerationRequest.builder()
                .model(ChatGptConfig.MODEL)
                .prompt(commentRequest.getPrompt())
                .n(ChatGptConfig.IMAGE_COUNT)
                .size(ChatGptConfig.IMAGE_SIZE)
                .build();

        HttpEntity<ImageGenerationRequest> requestHttpEntity = new HttpEntity<>(imageGenerationRequest, httpHeaders);

        ResponseEntity<ImageGenerationResponse> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.IMAGE_URL,
                requestHttpEntity,
                ImageGenerationResponse.class
        );

        System.out.println(responseEntity.toString());

        return responseEntity.getBody();
    }
}