package com.ssafy.imgMaker22.keyword;

import com.ssafy.imgMaker22.model.dto.GeneratedImage;
import com.ssafy.imgMaker22.model.dto.image.ImageGenerationResponseTest;
import com.ssafy.imgMaker22.model.dto.image.PromptRequest;
import com.ssafy.imgMaker22.model.dto.prompt.ImageRequest;
import com.ssafy.imgMaker22.model.dto.prompt.PromptDTO;
import com.ssafy.imgMaker22.model.service.*;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

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

    @Autowired
    private FileService fileUploadService;

    @Autowired
    private S3Uploader s3Uploader;

    @Test
    void ImageGenerationTest() throws IOException {
        List<ImageRequest> imageRequests = new ArrayList<>();
        imageRequests.add(new ImageRequest(resourceLoader.getResource("classpath:static/images/KakaoTalk_Photo_2023-12-19-01-26-47 001.jpeg").getURL().toString()));
        imageRequests.add(new ImageRequest(resourceLoader.getResource("classpath:static/images/KakaoTalk_Photo_2023-12-19-01-26-47 002.jpeg").getURL().toString()));
        imageRequests.add(new ImageRequest(resourceLoader.getResource("classpath:static/images/2023-12-19_01-23-56.jpeg").getURL().toString()));

        Map<String, List<PromptDTO>> promptDTOMap = new HashMap<>();

        List<PromptDTO> keywords = new ArrayList<>();
        cloudVisionService.getKeywords(imageRequests).subscribe((promptDTO -> keywords.add(promptDTO)));
        int i = 1;
        System.out.println("keywords");
        for (PromptDTO p: keywords) {
            System.out.println(i++ + "  = " + p);
        }

        List<PromptDTO> colors = new ArrayList<>();
        cloudVisionService.getColors(imageRequests).subscribe((promptDTO -> colors.add(promptDTO)));
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

    @Test
    void S3UploadTest() throws IOException {
        Path imagePath = Path.of("/Users/djlata/Projects/your2023/springboot project/imgMaker22/src/main/resources/static/images/KakaoTalk_Photo_2023-12-19-01-26-47 001.jpeg");
        // 이미지 파일을 바이트 배열로 읽기
        byte[] imageBytes = Files.readAllBytes(imagePath);
        // 바이트 배열을 Base64로 인코딩
        String base64Encoded = Base64.getEncoder().encodeToString(imageBytes);
        byte[] decodedBytes = java.util.Base64.getDecoder().decode(base64Encoded);

        System.out.println(fileUploadTest(decodedBytes));
        Assertions.assertThat(Arrays.toString(imageBytes)).isEqualTo(Arrays.toString(decodedBytes));
    }

    public String fileUploadTest(byte[] decodedBytes) throws IOException{
        String storedFileUrl = null;
        File file = convert(decodedBytes).get();
        // 같은 파일명이 중복되는지 확인하는 로직이 필요함
        storedFileUrl = s3Uploader.upload(file, "images"); // 경로명을 어떻게 해야 하지??
        removeNewFile(file);
        return storedFileUrl;
    }

    private Optional<File> convert(byte[] decodedBytes) throws IOException {
        File file = new File("KakaoTalk_Photo_2023-12-19-01-26-47 001.jpeg"); // 이름 (경로) 가져오고 (수정필요)
        if(file.createNewFile()) { // 같은 이름의 파일이 없다면
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(decodedBytes); // 데이터 넣어주고
            }
            return Optional.of(file); // 리턴
        }
        return Optional.empty();
    }

    private void removeNewFile(File targetFile) {
        targetFile.delete();
    }

}
