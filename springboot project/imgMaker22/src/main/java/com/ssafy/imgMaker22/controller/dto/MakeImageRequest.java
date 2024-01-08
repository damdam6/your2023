package com.ssafy.imgMaker22.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class MakeImageRequest {

//    private MultipartFile image1;
//    private MultipartFile image2;
//    private MultipartFile image3;
//    private MultipartFile image4;
    private List<Image> images;
    private String nickname;
    private String style;

    public MakeImageRequest(){}

    @Data
    public static class Image {
        private String url;
    }

//    public List<MultipartFile> getImages() {
//        List<MultipartFile> mFiles = new ArrayList<>();
//        try{
//            mFiles.add(this.image1);
//            mFiles.add(this.image2);
//            mFiles.add(this.image3);
//            mFiles.add(this.image4);
//        } catch (Exception e){
//            throw new NullPointerException();
//        }
//        return mFiles;
//    }
}
