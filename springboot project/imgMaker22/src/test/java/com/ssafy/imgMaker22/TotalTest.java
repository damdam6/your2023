package com.ssafy.imgMaker22;

import com.ssafy.imgMaker22.model.service.file.S3Uploader;
import com.ssafy.imgMaker22.model.entity.GeneratedImage;
import com.ssafy.imgMaker22.model.service.image.dto.ImageGenerationResponse;
import com.ssafy.imgMaker22.model.service.prompt.dto.ImageUrlDto;
import com.ssafy.imgMaker22.model.service.file.FileService;
import com.ssafy.imgMaker22.model.service.image.ImageGenerationService;
import com.ssafy.imgMaker22.model.service.prompt.PromptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
@Slf4j
public class TotalTest {

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
    void test() throws IOException {
        List<ImageUrlDto> imageUrls = new ArrayList<>();
//        imageUrls.add(new ImageRequest("https://drive.google.com/uc?export=download&id=1A8UkrtGgXHScTafP0ak_HNrlHADkf76v"));
//        imageUrls.add(new ImageRequest("https://drive.google.com/uc?export=download&id=1s3ngbgjUK7PO9yM59kvfcC8AQ48iZqTq"));
//        imageUrls.add(new ImageRequest("https://drive.google.com/uc?export=download&id=1jSwEp_bpt8-yr5rRCmrRm8aWwE2djgzM"));
//        imageUrls.add(new ImageRequest("https://drive.google.com/uc?export=download&id=11c9HW8WoYZ2dy0sTe7_ndwTOl5HvUI7P"));

//        imageUrls.add(new ImageRequest("https://drive.google.com/uc?export=download&id=1vEYs_3LDvdBgSyTZgX5kom6GS8a0Bj0f"));
//        imageUrls.add(new ImageRequest("https://drive.google.com/uc?export=download&id=1YuGkB3drmZfFOkbGW73EcdQN8zN3wevN"));
//        imageUrls.add(new ImageRequest("https://drive.google.com/uc?export=download&id=1vMWbiYercNQUBXRr4EwejjHC-p-NVTZT"));

        imageUrls.add(new ImageUrlDto("https://drive.google.com/uc?export=download&id=1aZQTRw-7xKqDFo5_mfimaupo5KHrXqLU"));
        imageUrls.add(new ImageUrlDto("https://drive.google.com/uc?export=download&id=1GBPK0oYQwS_AA7KXBiggSDgATTy2ttv9"));
        imageUrls.add(new ImageUrlDto("https://drive.google.com/uc?export=download&id=1oJdBf49cSUiPf2Yddt85ZKvMakBL_kfm"));
        imageUrls.add(new ImageUrlDto("https://drive.google.com/uc?export=download&id=1_r-2eCzqqGqVqo7bWaaO2EDKdvxhx0bx"));

        String style = "Cartoon Animation";
        String nickname = "damongsanga";
        GeneratedImage gImage = GeneratedImage.builder().nickname(nickname).style(style).build();

        String generatedPrompt = promptService.makePrompt(imageUrls, style);
        ImageGenerationResponse imageGenerationResponse = null;
        try {
            imageGenerationResponse = dalle3Service.makeImages(generatedPrompt);
            byte[] decodedBytes = java.util.Base64.getDecoder().decode(imageGenerationResponse.getData().get(0).getB64Json());
            fileService.fileUpload(decodedBytes, gImage);
        } catch (Exception e) { // 수정
            log.error("error message : {}", e.getMessage());
        }
        
    }

}
