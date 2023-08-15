package com.example.simple.model.comment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.simple.model.comment.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    Optional<CommentEntity> findByIdx(Long idx);
    
    List<CommentEntity> findByContent(String content);
    
    List<CommentEntity> findByPostEntity_Idx(Long postIdx);
    
    List<CommentEntity> findByUserEntity_Idx(Long userIdx);
    
    List<CommentEntity> findByUserEntity_IdxAndDeleteDateIsNullOrderByIdxDesc(Long userIdx);

    List<CommentEntity> findByUserEntity_IdxOrderByIdxDesc(Long userIdx);
    
    Optional<CommentEntity> findByIdxAndDeleteDateIsNullOrderByIdxDesc(Long idx);

    Optional<CommentEntity> findByIdxOrderByIdxDesc(Long idx);
}
