package com.ssafy.imgMaker22.prompt;

import com.ssafy.imgMaker22.model.dto.prompt.ImageRequest;
import com.ssafy.imgMaker22.model.service.PromptService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class PromptTest {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    @Qualifier("promptServiceGPT4")
    private PromptService promptService;

    @Test
    void promptGenerationTest() throws IOException {

        List<ImageRequest> imageUrls = new ArrayList<>();
        imageUrls.add(new ImageRequest("https://drive.google.com/uc?export=download&id=1A8UkrtGgXHScTafP0ak_HNrlHADkf76v"));
        imageUrls.add(new ImageRequest("https://drive.google.com/uc?export=download&id=1s3ngbgjUK7PO9yM59kvfcC8AQ48iZqTq"));
        imageUrls.add(new ImageRequest("https://drive.google.com/uc?export=download&id=1jSwEp_bpt8-yr5rRCmrRm8aWwE2djgzM"));
        imageUrls.add(new ImageRequest("https://drive.google.com/uc?export=download&id=11c9HW8WoYZ2dy0sTe7_ndwTOl5HvUI7P"));

        String style = "japanese animation";
        
        String prompt = promptService.makePromptTest(imageUrls, style);

        log.info("prompt : {}", prompt);
    }
}
