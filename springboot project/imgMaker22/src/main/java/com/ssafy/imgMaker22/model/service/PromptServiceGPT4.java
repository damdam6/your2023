package com.ssafy.imgMaker22.model.service;

import com.ssafy.imgMaker22.config.GPT4Config;
import com.ssafy.imgMaker22.model.dto.GeneratedImage;
import com.ssafy.imgMaker22.model.dto.prompt.ImageRequest;
import com.ssafy.imgMaker22.model.dto.prompt.PromptGenerationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class PromptServiceGPT4 implements PromptService{


    private final RestTemplate restTemplate;

    @Value("${openai.dalle.api}")
    private String apiKey;

    @Override
    public String makePrompt(List<ImageRequest> imageURLS, String style) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(GPT4Config.MEDIA_TYPE));
        httpHeaders.add(GPT4Config.AUTHORIZATION, GPT4Config.BEARER + apiKey);

        System.out.println(GPT4Config.createRequestParam(GPT4Config.createPrompt(style), imageURLS));

        HttpEntity<String> requestHttpEntity = new HttpEntity<>(GPT4Config.createRequestParam(GPT4Config.createPrompt(style), imageURLS), httpHeaders);

        ResponseEntity<PromptGenerationResponse> responseEntity = restTemplate.postForEntity(
                GPT4Config.IMAGE_URL, requestHttpEntity, PromptGenerationResponse.class
        );

        String prompt = Objects.requireNonNull(responseEntity.getBody()).getContent();
        System.out.println("GPT 4 response : " + prompt);

        return prompt;
    }

    @Override
    public String makePromptTest(List<ImageRequest> imageURLS, String style){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(GPT4Config.MEDIA_TYPE));
        httpHeaders.add(GPT4Config.AUTHORIZATION, GPT4Config.BEARER + apiKey);

        System.out.println(GPT4Config.createRequestParam(GPT4Config.createPrompt(style), imageURLS));

        HttpEntity<String> requestHttpEntity = new HttpEntity<>(GPT4Config.createRequestParam(GPT4Config.createPrompt(style), imageURLS), httpHeaders);

        ResponseEntity<PromptGenerationResponse> responseEntity = restTemplate.postForEntity(
                GPT4Config.IMAGE_URL, requestHttpEntity, PromptGenerationResponse.class
        );

        System.out.println("GPT 4 response : " + Objects.requireNonNull(responseEntity.getBody()).getContent());

        return Objects.requireNonNull(responseEntity.getBody()).getContent();
    }


}
