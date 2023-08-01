package com.example.simple.domain.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.simple.domain.main.dto.MainPostDTO;
import com.example.simple.domain.main.dto.ReqPostDTO;
import com.example.simple.domain.main.dto.ResMainPostDTO;
import com.example.simple.domain.main.dto.ResPostDTO;
import com.example.simple.domain.main.dto.ReqPostDTO.ResCommentDTO;
import com.example.simple.model.comment.entity.CommentEntity;
import com.example.simple.model.comment.repository.CommentRepository;
import com.example.simple.model.post.entity.PostEntity;
import com.example.simple.model.post.repository.PostRepository;

@Service
public class MainService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    public ResMainPostDTO getPostListData() {

        List<PostEntity> postEntityList = postRepository.findByDeleteDateIsNull();

        List<MainPostDTO> mainPostDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntityList) {
            MainPostDTO mainPostDTO = new MainPostDTO(postEntity.getIdx(), postEntity.getTitle(),
                    postEntity.getUserEntity().getId());

            mainPostDTOList.add(mainPostDTO);
        }

        return new ResMainPostDTO(mainPostDTOList);

    }

    public ResPostDTO getPostData(Long postIdx) {

        Optional<PostEntity> postEntityOptional = postRepository.findByIdxAndDeleteDateIsNull(postIdx);

        List<CommentEntity> commentEntityList = commentRepository.findByPostEntity_Idx(postIdx);

        List<ResCommentDTO> resCommentDTOList = new ArrayList<>();

        for (CommentEntity commentEntity : commentEntityList) {
            ResCommentDTO resCommentDTO = new ResCommentDTO(commentEntity.getUserEntity().getId(),
                    commentEntity.getContent(), commentEntity.getCreateDate());

            resCommentDTOList.add(resCommentDTO);
        }

        if (postEntityOptional.isEmpty()) {
            throw new RuntimeException("존재하지 않은 게시물 입니다");
        }

        ReqPostDTO reqPostDTO = new ReqPostDTO(postEntityOptional.get().getTitle(),
                postEntityOptional.get().getContent(), postEntityOptional.get().getUserEntity().getId(),
                postEntityOptional.get().getCreateDate(), resCommentDTOList);

        return new ResPostDTO(reqPostDTO);

    }

}
