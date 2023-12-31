package com.ssafy.imgMaker22.model.repository;

import com.ssafy.imgMaker22.model.entity.GeneratedImage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainRepository {

    public void save(GeneratedImage gImage);

    public List<GeneratedImage> selectAllImages();

}
