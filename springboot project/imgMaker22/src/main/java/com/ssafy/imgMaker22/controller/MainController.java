package com.ssafy.imgMaker22.controller;

import com.ssafy.imgMaker22.model.dto.GeneratedImage;
import com.ssafy.imgMaker22.model.dto.image.ImageGenerationResponse;
import com.ssafy.imgMaker22.model.dto.image.PromptDTO;
import com.ssafy.imgMaker22.model.dto.image.PromptRequest;
import com.ssafy.imgMaker22.model.dto.prompt.ImageRequest;
import com.ssafy.imgMaker22.model.service.FileUploadService;
import com.ssafy.imgMaker22.model.service.ImageGenerationService;
import com.ssafy.imgMaker22.model.service.KeywordGenerationService;
import com.ssafy.imgMaker22.model.service.PromptService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/vision/api")
public class MainController {

    private KeywordGenerationService cloudVisionService;
    private PromptService promptService;
    private ImageGenerationService chatGptService;
    private FileUploadService fileUploadService;

    @PostMapping("/getLabelDetection")
    public ResponseEntity makeImage(HttpServletRequest request, HttpServletResponse response, @RequestBody List<ImageRequest> imageRequests) {

        String nickname = "damongsanga"; // 수정
        String style = "realistic";
        GeneratedImage gImage = GeneratedImage.builder().nickname(nickname).style(style).build();

        List<PromptDTO> promptDTOs = cloudVisionService.makePrompt(imageRequests);
        PromptRequest promptRequest = PromptRequest.builder().prompt(promptService.makePrompt(gImage, promptDTOs, style)).build();

        ImageGenerationResponse imageGenerationResponse = null;
        try {
            imageGenerationResponse = chatGptService.makeImages(promptRequest);
            byte[] decodedBytes = java.util.Base64.getDecoder().decode(imageGenerationResponse.getData());
            ; // 임시
            fileUploadService.fileUpload(decodedBytes, gImage);

        } catch (Exception e){ // 수정
            e.printStackTrace(); // 수정
        }




        return new ResponseEntity<ImageGenerationResponse>(imageGenerationResponse, HttpStatus.OK);
    }

}