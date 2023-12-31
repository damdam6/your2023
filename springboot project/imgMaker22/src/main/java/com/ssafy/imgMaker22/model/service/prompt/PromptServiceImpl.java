package com.ssafy.imgMaker22.model.service.prompt;

import com.ssafy.imgMaker22.model.entity.GeneratedImage;
import com.ssafy.imgMaker22.model.service.keyword.dto.PromptDto;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * GPT4에 이미지를 직접 넣을 수 있는 기능이 생기면서 deprecated
 */

@Service
@Deprecated
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

}
