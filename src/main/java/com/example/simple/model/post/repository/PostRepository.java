package com.example.simple.model.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.simple.model.post.entity.PostEntity;
import java.util.List;
import java.util.Optional;


public interface PostRepository extends JpaRepository<PostEntity, Long> {
    Optional<PostEntity> findByIdx(Long idx);

    List<PostEntity> findByTitle(String title);

    List<PostEntity> findByContent(String content);

    List<PostEntity> findByUserEntity_Idx(Long userIdx);

    List<PostEntity> findByTitleContaining(String title);
    
    List<PostEntity> findByContentContaining(String content);
    
    List<PostEntity> findByTitleContainingOrContentContaining(String title, String content);

    Optional<PostEntity> findByIdxAndDeleteDateIsNull(Long idx);

    List<PostEntity> findByDeleteDateIsNull();
    

    
}
