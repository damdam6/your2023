package com.ssafy.imgMaker22.model.repository;

import com.ssafy.imgMaker22.model.dto.GeneratedImage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainRepository {

    public int save(GeneratedImage gImage);

    public List<GeneratedImage> selectAllImages();

}
