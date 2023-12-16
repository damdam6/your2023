package com.ssafy.imgMaker22.controller;

import com.ssafy.imgMaker22.model.dto.image.ImageGenerationResponse;
import com.ssafy.imgMaker22.model.dto.image.PromptRequest;
import com.ssafy.imgMaker22.model.dto.prompt.ImageRequest;
import com.ssafy.imgMaker22.model.service.CloudVisionService;
import com.ssafy.imgMaker22.model.service.DALLEService;
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
public class VisionController {

    private CloudVisionService cloudVisionService;
    private DALLEService chatGptService;

    @PostMapping("/getLabelDetection")
    public ResponseEntity makeImage(HttpServletRequest request, HttpServletResponse response, @RequestBody List<ImageRequest> imageRequests) {

        PromptRequest promptRequest = cloudVisionService.makePrompt(imageRequests);

        ImageGenerationResponse imageGenerationResponse = null;
        try {
            imageGenerationResponse = chatGptService.makeImages(promptRequest);
        } catch (Exception e){ // 수정
            e.printStackTrace(); // 수정
        }

        return new ResponseEntity<ImageGenerationResponse>(imageGenerationResponse, HttpStatus.OK);
    }

}