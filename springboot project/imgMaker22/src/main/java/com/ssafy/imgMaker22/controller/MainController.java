package com.ssafy.imgMaker22.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.imgMaker22.model.dto.GeneratedImage;
import com.ssafy.imgMaker22.model.dto.image.ImageGenerationResponse;
import com.ssafy.imgMaker22.model.dto.image.PromptRequest;
import com.ssafy.imgMaker22.model.dto.prompt.ImageRequest;
import com.ssafy.imgMaker22.model.dto.prompt.PromptDTO;
import com.ssafy.imgMaker22.model.service.FileService;
import com.ssafy.imgMaker22.model.service.ImageGenerationService;
import com.ssafy.imgMaker22.model.service.KeywordGenerationService;
import com.ssafy.imgMaker22.model.service.PromptService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/vision/api")
public class MainController {

    private KeywordGenerationService cloudVisionService;
    private PromptService promptService;
    private ImageGenerationService dalle3Service;
    private FileService fileService;
    private final ObjectMapper mapper;


    @PostMapping("/getLabelDetection")
    public ResponseEntity makeImage(HttpServletRequest request, HttpServletResponse response, @RequestBody List<ImageRequest> imageRequests) {

        String nickname = mapper.convertValue(request.getAttribute("nickname"), String.class);
        String style = mapper.convertValue(request.getAttribute("style"), String.class);
        GeneratedImage gImage = GeneratedImage.builder().nickname(nickname).style(style).build();

        // 프롬프트를 위한 키워드, 색상 추출 및 프롬프트 생성
        Map<String, List<PromptDTO>> promptDTOMap = new HashMap<>();
        List<PromptDTO> keywords = new ArrayList<>();
        cloudVisionService.getKeywords(imageRequests).subscribe((promptDTO -> keywords.add(promptDTO)));
        promptDTOMap.put("keywords", keywords);
        List<PromptDTO> colors = new ArrayList<>();
        cloudVisionService.getColors(imageRequests).subscribe((promptDTO -> colors.add(promptDTO)));
        promptDTOMap.put("colors", colors);

        PromptRequest promptRequest = PromptRequest.builder().prompt(promptService.makePrompt(gImage, promptDTOMap, style)).build();

        // 이미지 생성 및 저
        ImageGenerationResponse imageGenerationResponse = null;
        try {
            imageGenerationResponse = dalle3Service.makeImages(promptRequest);
            byte[] decodedBytes = java.util.Base64.getDecoder().decode(imageGenerationResponse.getData().get(0));
            fileService.fileUpload(decodedBytes, gImage);
        } catch (Exception e) { // 수정
            e.printStackTrace(); // 수정
        }

        return new ResponseEntity<ImageGenerationResponse>(imageGenerationResponse, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllImages(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<List<String>>(fileService.getAllFileUrl(), HttpStatus.OK);
    }
}