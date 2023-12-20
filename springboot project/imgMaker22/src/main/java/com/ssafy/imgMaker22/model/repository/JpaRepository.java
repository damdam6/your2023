package com.ssafy.imgMaker22.model.repository;

import com.ssafy.imgMaker22.model.dto.GeneratedImage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaRepository implements MainRepository{

    @PersistenceContext
    private final EntityManager em;

    @Override
    public void save(GeneratedImage gImage) {
        em.persist(gImage);
    }

    @Override
    public List<GeneratedImage> selectAllImages() {
        return em.createQuery("Select g from GeneratedImage g", GeneratedImage.class).getResultList();
    }
}
