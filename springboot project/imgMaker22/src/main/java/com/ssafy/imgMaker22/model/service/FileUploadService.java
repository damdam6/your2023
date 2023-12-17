package com.ssafy.imgMaker22.model.service;

import com.ssafy.imgMaker22.model.dto.GeneratedImage;

import java.io.IOException;

public interface FileUploadService {

    public String fileUpload(byte[] decodedBytes, GeneratedImage gImage) throws IOException;


}
