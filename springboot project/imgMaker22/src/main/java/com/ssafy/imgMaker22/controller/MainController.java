package com.ssafy.imgMaker22.controller;

import com.ssafy.imgMaker22.controller.dto.ImageResponse;
import com.ssafy.imgMaker22.controller.dto.MakeImageRequest;
import com.ssafy.imgMaker22.model.entity.GeneratedImage;
import com.ssafy.imgMaker22.model.service.image.dto.ImageGenerationResponse;
import com.ssafy.imgMaker22.model.service.prompt.dto.ImageUrlDto;
import com.ssafy.imgMaker22.model.service.file.FileService;
import com.ssafy.imgMaker22.model.service.image.ImageGenerationService;
import com.ssafy.imgMaker22.model.service.prompt.PromptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
@Slf4j
public class MainController {

    private final PromptService promptService;
    private final ImageGenerationService dalle3Service;
    private final FileService fileService;

    @PostMapping("/image")
    public ResponseEntity makeImage(@RequestBody MakeImageRequest requests) {

        String nickname = requests.getNickname();
        String style = requests.getStyle();
        GeneratedImage gImage = GeneratedImage.builder().nickname(nickname).style(style).build();

        List<ImageUrlDto> imageUrls = requests.getImages().stream().
                map(s -> new ImageUrlDto(s.getUrl())).collect(Collectors.toList());
        String generatedPrompt = promptService.makePrompt(imageUrls, style);

        // 이미지 생성 및 저장
        ImageGenerationResponse imageGenerationResponse = null;
        String url = null;
        try {
            imageGenerationResponse = dalle3Service.makeImages(generatedPrompt);
            byte[] decodedBytes = Base64.getDecoder().decode(imageGenerationResponse.getData().get(0).getB64Json());
            url = fileService.fileUpload(decodedBytes, gImage);
        } catch (Exception e) { // 수정
            log.error("error message : {}", e.getMessage());
        }

        if (url == null) return new ResponseEntity<ImageResponse>(new ImageResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<ImageResponse>(new ImageResponse(url), HttpStatus.OK);
    }

    @GetMapping("/allImages")
    public ResponseEntity getAllImages() {
        return new ResponseEntity<List<ImageResponse>>(
                fileService.getAllFileUrl().stream().map(ImageResponse::new).collect(Collectors.toList()),
                HttpStatus.OK);
    }
}