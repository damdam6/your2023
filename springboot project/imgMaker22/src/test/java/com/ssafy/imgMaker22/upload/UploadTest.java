package com.ssafy.imgMaker22.upload;

import com.ssafy.imgMaker22.config.S3Uploader;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

public class UploadTest {

    @Autowired
    private S3Uploader s3Uploader;

    @Test
    void S3UploadTest() throws IOException {
        Path imagePath = Path.of("/Users/djlata/Projects/your2023/springboot project/imgMaker22/src/main/resources/static/images/KakaoTalk_Photo_2023-12-19-01-26-47 001.jpeg");
        // 이미지 파일을 바이트 배열로 읽기
        byte[] imageBytes = Files.readAllBytes(imagePath);
        // 바이트 배열을 Base64로 인코딩
        String base64Encoded = Base64.getEncoder().encodeToString(imageBytes);
        byte[] decodedBytes = java.util.Base64.getDecoder().decode(base64Encoded);

        System.out.println(fileUploadTest(decodedBytes));
        Assertions.assertThat(Arrays.toString(imageBytes)).isEqualTo(Arrays.toString(decodedBytes));
    }

    public String fileUploadTest(byte[] decodedBytes) throws IOException{
        String storedFileUrl = null;
        File file = convert(decodedBytes).get();
        // 같은 파일명이 중복되는지 확인하는 로직이 필요함
        storedFileUrl = s3Uploader.upload(file, "images"); // 경로명을 어떻게 해야 하지??
        removeNewFile(file);
        return storedFileUrl;
    }

    private Optional<File> convert(byte[] decodedBytes) throws IOException {
        File file = new File("KakaoTalk_Photo_2023-12-19-01-26-47 001.jpeg"); // 이름 (경로) 가져오고 (수정필요)
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
}
