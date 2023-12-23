package com.ssafy.imgMaker22;

import com.ssafy.imgMaker22.config.S3Uploader;
import com.ssafy.imgMaker22.model.dto.GeneratedImage;
import com.ssafy.imgMaker22.model.dto.image.ImageGenerationResponse;
import com.ssafy.imgMaker22.model.dto.image.PromptRequest;
import com.ssafy.imgMaker22.model.dto.prompt.ImageRequest;
import com.ssafy.imgMaker22.model.service.FileService;
import com.ssafy.imgMaker22.model.service.ImageGenerationService;
import com.ssafy.imgMaker22.model.service.KeywordGenerationService;
import com.ssafy.imgMaker22.model.service.PromptService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
public class TotalTest {

    @Autowired
    private KeywordGenerationService cloudVisionService;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private PromptService promptService;

    @Autowired
    private ImageGenerationService dalle3Service;

    @Autowired
    private FileService fileService;

    @Autowired
    private S3Uploader s3Uploader;

    @Test
    void ImageGenerationTest() throws IOException {
        List<ImageRequest> imageUrls = new ArrayList<>();
        imageUrls.add(new ImageRequest("https://drive.google.com/uc?export=download&id=1A8UkrtGgXHScTafP0ak_HNrlHADkf76v"));
        imageUrls.add(new ImageRequest("https://drive.google.com/uc?export=download&id=1s3ngbgjUK7PO9yM59kvfcC8AQ48iZqTq"));
        imageUrls.add(new ImageRequest("https://drive.google.com/uc?export=download&id=1jSwEp_bpt8-yr5rRCmrRm8aWwE2djgzM"));
        imageUrls.add(new ImageRequest("https://drive.google.com/uc?export=download&id=11c9HW8WoYZ2dy0sTe7_ndwTOl5HvUI7P"));

        String style = "japanese animation";
        String nickname = "damongsanga";
        GeneratedImage gImage = GeneratedImage.builder().nickname(nickname).style(style).build();

        PromptRequest promptRequest = PromptRequest.builder().prompt(promptService.makePrompt(imageUrls, style)).build();
        ImageGenerationResponse imageGenerationResponse = null;
        try {
            imageGenerationResponse = dalle3Service.makeImages(promptRequest);
            byte[] decodedBytes = java.util.Base64.getDecoder().decode(imageGenerationResponse.getData().get(0).getB64_json());
            fileService.fileUpload(decodedBytes, gImage);
        } catch (Exception e) { // 수정
            e.printStackTrace(); // 수정
        }
        
    }

}
