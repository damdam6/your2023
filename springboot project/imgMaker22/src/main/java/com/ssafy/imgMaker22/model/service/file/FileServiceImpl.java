package com.ssafy.imgMaker22.model.service.file;

import com.ssafy.imgMaker22.model.entity.GeneratedImage;
import com.ssafy.imgMaker22.model.repository.MainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private MainRepository mainRepository;
    private S3Uploader s3Uploader;

    @Autowired
    public FileServiceImpl(MainRepository mainRepository, S3Uploader s3Uploader) {
        this.mainRepository = mainRepository;
        this.s3Uploader = s3Uploader;
    }

    @Transactional
    public String fileUpload(byte[] decodedBytes, GeneratedImage gImage) throws IOException{
        String storedFileUrl = null;
        String filename = gImage.getNickname()+"_generatedImage_"+System.currentTimeMillis();
        File file = convert(decodedBytes, filename).get();
        // 같은 파일명이 중복되는지 확인하는 로직이 필요함
        storedFileUrl = s3Uploader.upload(file, "images"); // 경로명을 어떻게 해야 하지??
        gImage.setUrl(storedFileUrl);
        removeNewFile(file);
        mainRepository.save(gImage);
        return storedFileUrl;
    }

    private Optional<File> convert(byte[] decodedBytes, String filename) throws IOException {
        File file = new File(filename); // 이름 (경로) 가져오고 (수정필요)
        if(file.createNewFile()) { // 같은 이름의 파일이 없다면
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(decodedBytes); // 데이터 넣어주고
            }
            return Optional.of(file); // 리턴
        }
        return Optional.empty();
    }

    private void removeNewFile(File targetFile) {
        targetFile.delete();
    }

    public List<String> getAllFileUrl(){
        return mainRepository.selectAllImages().stream().map(GeneratedImage::getUrl).toList();
    }
}
