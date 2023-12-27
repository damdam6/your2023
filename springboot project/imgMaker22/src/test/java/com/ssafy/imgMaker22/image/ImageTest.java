package com.ssafy.imgMaker22.image;

import com.ssafy.imgMaker22.model.dto.image.ImageGenerationResponseTest;
import com.ssafy.imgMaker22.model.dto.image.PromptRequest;
import com.ssafy.imgMaker22.model.service.ImageGenerationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ImageTest {

    @Autowired
    private ImageGenerationService dalle3Service;

    @Test
    void imgGenerationOnlyTest(){
        String prompt = "\"Create a vibrant Japanese anime style scene that celebrates a momentous occasion. The centerpiece is a pair of characters in formal graduation attire, reminiscent of the blue gowns and caps seen in the second image, standing in a field that suggests the vibrant green of the athletic field with the university building in the background, adapted into a grand, stylized academic institution. They are holding diplomas with intricate borders like those seen in the first image and bouquets of flowers to signify their success.\n" +
                "\n" +
                "Above them, design an elaborate banner, using the blue and white color scheme from the third image, that reads 'Congratulations Graduates!' in bold, stylized lettering. Make the scene festive with some people in the background tossing their caps in the air, capturing the joy of graduation.\n" +
                "\n" +
                "In the sky, merge the idea of an open future and adventure by transforming the clear blue sky and rugged, snowy landscape seen in the fourth image into a distant, breathtaking view beyond the campus, hinting at new horizons and the vast possibilities ahead for the graduating students.\n" +
                "\n" +
                "Ensure the characters exhibit the classic anime aesthetic with large expressive eyes, detailed hair, and dynamic poses to convey excitement and happiness.\"";

        PromptRequest promptRequest = PromptRequest.builder().prompt(prompt).build();
        ImageGenerationResponseTest imageGenerationResponse = null;
        try {
            imageGenerationResponse = dalle3Service.makeImagesURLTEST(promptRequest);
            log.info("URL : {}", imageGenerationResponse.getData().get(0).getUrl());
        } catch (Exception e){ // 수정
            log.error("error message : {}", e.getMessage());
        }
    }
}
