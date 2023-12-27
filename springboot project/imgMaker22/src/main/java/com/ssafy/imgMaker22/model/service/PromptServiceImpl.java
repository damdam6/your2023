package com.ssafy.imgMaker22.model.service;

import com.ssafy.imgMaker22.model.dto.GeneratedImage;
import com.ssafy.imgMaker22.model.dto.prompt.PromptDto;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class PromptServiceImpl{

    public String makePrompt(GeneratedImage gImage, Map<String, List<PromptDto>> promptDTOMap, String style) {

        // 키워드 주입
        StringBuilder sb = new StringBuilder();
        sb.append("Generate a prompt for dall e 3. I want to make ").append(style + " image.");

        sb.append("There is a Man in the center of the image. Combine the following keywords to illustrate the image :");
        List<PromptDto> keywords = promptDTOMap.get("keywords");
        Collections.sort(keywords);
        for (PromptDto promptDTO : keywords){
            sb.append(" ").append(promptDTO.getPrompt()).append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(".");
        return sb.toString();
    }

    public String makePromptTest(List<String> imageURLS, String style) {
        System.out.println("not here");
        return null;
    }
}
