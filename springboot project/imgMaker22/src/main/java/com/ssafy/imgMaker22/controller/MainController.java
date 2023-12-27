package com.ssafy.imgMaker22.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.imgMaker22.model.dto.GeneratedImage;
import com.ssafy.imgMaker22.model.dto.image.ImageGenerationResponse;
import com.ssafy.imgMaker22.model.dto.image.PromptRequest;
import com.ssafy.imgMaker22.model.dto.prompt.ImageRequest;
import com.ssafy.imgMaker22.model.service.FileService;
import com.ssafy.imgMaker22.model.service.ImageGenerationService;
import com.ssafy.imgMaker22.model.service.KeywordGenerationService;
import com.ssafy.imgMaker22.model.service.PromptService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/vision/api")
@Slf4j
public class MainController {

    private PromptService promptService;
    private ImageGenerationService dalle3Service;
    private FileService fileService;
    private final ObjectMapper mapper;


    @PostMapping("/getImage")
    public ResponseEntity makeImage(HttpServletRequest request, HttpServletResponse response, @RequestBody List<ImageRequest> imageRequests) {

        String nickname = mapper.convertValue(request.getAttribute("nickname"), String.class);
        String style = mapper.convertValue(request.getAttribute("style"), String.class);
        GeneratedImage gImage = GeneratedImage.builder().nickname(nickname).style(style).build();

        List<ImageRequest> imageUrls = Arrays.stream(mapper.convertValue(request.getAttribute("images"), String[].class)).
                map(s -> new ImageRequest(s)).collect(Collectors.toList());
        PromptRequest promptRequest = PromptRequest.builder().prompt(promptService.makePrompt(imageUrls, style)).build();

        // 이미지 생성 및 저장
        ImageGenerationResponse imageGenerationResponse = null;
        String url = null;
        try {
            imageGenerationResponse = dalle3Service.makeImages(promptRequest);
            byte[] decodedBytes = Base64.getDecoder().decode(imageGenerationResponse.getData().get(0).getB64Json());
            url = fileService.fileUpload(decodedBytes, gImage);
        } catch (Exception e) { // 수정
            log.error("error message : {}", e.getMessage());
        }
        if (url == null) return new ResponseEntity<String>(url, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<String>(url, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllImages(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<List<String>>(fileService.getAllFileUrl(), HttpStatus.OK);
    }
}