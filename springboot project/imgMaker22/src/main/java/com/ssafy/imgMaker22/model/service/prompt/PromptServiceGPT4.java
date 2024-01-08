package com.ssafy.imgMaker22.model.service.prompt;

import com.ssafy.imgMaker22.config.GPT4Config;
import com.ssafy.imgMaker22.model.service.prompt.dto.ImageUrlDto;
import com.ssafy.imgMaker22.model.service.prompt.dto.PromptGenerationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class PromptServiceGPT4 implements PromptService{

    private final WebClient webClient;

    private String apiKey;

    @Autowired
    public PromptServiceGPT4(WebClient.Builder webClientBuilder, @Value("${openai.dalle.api}") String apiKey) {
        this.apiKey = apiKey;
        this.webClient = webClientBuilder.baseUrl(GPT4Config.CHAT_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, GPT4Config.MEDIA_TYPE)
                .defaultHeader(GPT4Config.AUTHORIZATION, GPT4Config.BEARER + apiKey)
                .build();
    }

    @Override
    public String makePrompt(List<ImageUrlDto> imageURLS, String style) {

        log.info("imageURL INFO : {}", imageURLS);
        String requestParam = GPT4Config.createRequestParam(GPT4Config.createPrompt(style), imageURLS);
        String prompt = fetchPrompt(requestParam).block();
        log.info("GPT 4 response : {}", prompt);
        return prompt;
    }

    private Mono<String> fetchPrompt(String requestParam){
        return webClient.post()
                .bodyValue(requestParam)
                .retrieve()
                .bodyToMono(PromptGenerationResponse.class)
                .map(response -> Objects.requireNonNull(response).getContent());
    }

}
