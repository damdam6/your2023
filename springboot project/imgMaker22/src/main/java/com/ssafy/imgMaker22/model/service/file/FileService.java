package com.ssafy.imgMaker22.model.service.file;

import com.ssafy.imgMaker22.model.entity.GeneratedImage;

import java.io.IOException;
import java.util.List;

public interface FileService {

    public String fileUpload(byte[] decodedBytes, GeneratedImage gImage) throws IOException;

    public List<String> getAllFileUrl();

}
