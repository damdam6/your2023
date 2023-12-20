package com.ssafy.imgMaker22.keyword;

import com.ssafy.imgMaker22.model.dto.GeneratedImage;
import com.ssafy.imgMaker22.model.dto.image.ImageGenerationResponseTest;
import com.ssafy.imgMaker22.model.dto.image.PromptRequest;
import com.ssafy.imgMaker22.model.dto.prompt.ImageRequest;
import com.ssafy.imgMaker22.model.dto.prompt.PromptDTO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RequiredArgsConstructor
public class VisionTest {

    @Autowired
    private KeywordGenerationService cloudVisionService;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private PromptService promptService;

    @Autowired
    private ImageGenerationService dalle3Service;


    @Test
    void ImageGenerationTest() throws IOException {
        List<ImageRequest> imageRequests = new ArrayList<>();
        imageRequests.add(new ImageRequest(resourceLoader.getResource("classpath:static/images/KakaoTalk_Photo_2023-12-19-01-26-47 001.jpeg").getURL().toString()));
        imageRequests.add(new ImageRequest(resourceLoader.getResource("classpath:static/images/KakaoTalk_Photo_2023-12-19-01-26-47 002.jpeg").getURL().toString()));
        imageRequests.add(new ImageRequest(resourceLoader.getResource("classpath:static/images/2023-12-19_01-23-56.jpeg").getURL().toString()));

        Map<String, List<PromptDTO>> promptDTOMap = new HashMap<>();

        List<PromptDTO> keywords = cloudVisionService.getKeywords(imageRequests);
        int i = 1;
        System.out.println("keywords");
        for (PromptDTO p: keywords) {
            System.out.println(i++ + "  = " + p);
        }

        List<PromptDTO> colors = cloudVisionService.getColors(imageRequests);
        i = 1;
        System.out.println("colors");
        for (PromptDTO p: colors) {
            System.out.println(i++ + "  = " + p);
        }

        promptDTOMap.put("keywords", keywords);
        promptDTOMap.put("colors", colors);

        GeneratedImage gImage = GeneratedImage.builder().nickname("damongsanga").style("realistic").build();
        String prompt = promptService.makePrompt(gImage, promptDTOMap, "pen-sketch");
        System.out.println(prompt);

        PromptRequest promptRequest = PromptRequest.builder().prompt(prompt).build();
        ImageGenerationResponseTest imageGenerationResponse = null;
        try {
            imageGenerationResponse = dalle3Service.makeImagesURLTEST(promptRequest);
            System.out.println("URL : " + imageGenerationResponse.getData().get(0).getUrl());
        } catch (Exception e){ // 수정
            e.printStackTrace(); // 수정
        }

//        return promptDTOMap;
    }

    @Test
    void test2(){
        PromptRequest promptRequest = PromptRequest.builder().prompt("Generate a captivating watercolor painting-style image featuring the keywords: Wheel, Concert, Toy. In the heart of the scene, include a happy and vibrant representation of a 28-year-old Asian male, symbolizing the requester. Establish the main color of the image as RGB(25,132,100) to define the predominant color scheme. Embrace the fluid and expressive qualities of watercolor painting, using dynamic strokes and vivid hues to bring the composition to life. Seamlessly integrate Wheel, Concert, and Toy into the artwork, with the cheerful male character serving as the central focal point. Capture the essence of joy, spontaneity, and the unique personality of the requester within the watercolor painting style and the specified color palette.").build();
        ImageGenerationResponseTest imageGenerationResponse = null;
        try {
            imageGenerationResponse = dalle3Service.makeImagesURLTEST(promptRequest);
            System.out.println("URL : " + imageGenerationResponse.getData().get(0).getUrl());
        } catch (Exception e){ // 수정
            e.printStackTrace(); // 수정
        }
    }

}
