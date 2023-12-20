package com.ssafy.imgMaker22.model.service;

import com.ssafy.imgMaker22.model.dto.GeneratedImage;
import com.ssafy.imgMaker22.model.dto.prompt.PromptDTO;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class PromptServiceImpl implements PromptService{





    @Override
    public String makePrompt(GeneratedImage gImage, Map<String, List<PromptDTO>> promptDTOMap, String style) {

        // 키워드 주입
        StringBuilder sb = new StringBuilder();
        sb.append("Generate a prompt for dall e 3. I want to make ").append(style + " image.");

//        // 색 주입
//        List<PromptDTO> colors = promptDTOMap.get("colors");
//        sb.append(" Use following RGB Colors [");
//        for (PromptDTO promptDTO : colors){
//            sb.append(" ").append(promptDTO.getPrompt()).append(",");
//        }
//        sb.deleteCharAt(sb.length()-1);
//
//        sb.append("] to illustrate. Erase any color Table. ");

        sb.append("There is a Man in the center of the image. Combine the following keywords to illustrate the image :");
        List<PromptDTO> keywords = promptDTOMap.get("keywords");
        Collections.sort(keywords);
        for (PromptDTO promptDTO : keywords){
            sb.append(" ").append(promptDTO.getPrompt()).append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(".");

        gImage.setPrompt(sb.toString());

        return sb.toString();
    }
}
