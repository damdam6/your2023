package com.ssafy.imgMaker22.util;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    @Transactional
    public static String makeUrl(File file) {
        String url = file.getPath();
        return url;
    }

    public static List<File> convertToFiles(List<MultipartFile> mFiles, String nickname) throws IOException {
        List<File> files = new ArrayList<>();
        for (MultipartFile mFile : mFiles) {
            String filename = mFile.getOriginalFilename() + "_" + nickname + "_"+ System.currentTimeMillis();
            File file = new File(filename);
            mFile.transferTo(file);
            files.add(file);
        }
        return files;
    }

    public static void removeNewFiles(List<File> targetFiles) {
        targetFiles.forEach(File::delete);
    }

    public static void removeNewFile(File targetFile) {
        targetFile.delete();
    }

}
