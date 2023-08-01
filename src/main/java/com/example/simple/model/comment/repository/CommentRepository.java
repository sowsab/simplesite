package com.example.simple.model.comment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.simple.model.comment.entity.CommentEntity;


@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    
    Optional<CommentEntity> findByIdx(Long idx);

    List<CommentEntity> findByContent(String content);

    List<CommentEntity> findByPostEntity_Idx(Long postIdx);

    List<CommentEntity> findByUserEntity_Idx(Long userIdx);

}
