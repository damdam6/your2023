package com.ssafy.imgMaker22.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/chat-gpt/api")
public class ChatGptController {

//    @PostMapping("/image-generation")
//    public ResponseEntity sendComment(
//            Locale locale,
//            HttpServletRequest request,
//            HttpServletResponse response,
//            @RequestBody PromptRequest commentRequest) {
//        ImageGenerationResponse imageGenerationResponse = null;
//        try {
//            imageGenerationResponse = chatGptService.makeImages(commentRequest);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
}

