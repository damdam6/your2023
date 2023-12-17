package com.ssafy.imgMaker22.model.service;

import com.ssafy.imgMaker22.model.dto.GeneratedImage;
import com.ssafy.imgMaker22.model.dto.image.PromptDTO;

import java.util.Collections;
import java.util.List;

public class PromptServiceImpl implements PromptService{

    @Override
    public String makePrompt(GeneratedImage gImage, List<PromptDTO> promptDTOs, String style) {
        Collections.sort(promptDTOs);

        StringBuilder sb = new StringBuilder();
        sb.append("Illustrate ").append(style);
        for (PromptDTO promptDTO : promptDTOs){
            sb.append(promptDTO.getKeyword()).append(" ");
        }
        sb.append(". Write 'your 2023' on the right upper side of the image in matching style of the image.");
        gImage.setPrompt(sb.toString());

        return sb.toString();
    }
}
