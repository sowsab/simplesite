package com.example.simple.domain.main.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.example.simple.common.dto.LoginDTO;
import com.example.simple.common.exception.CustomNotFoundException;
import com.example.simple.common.exception.UnauthorizedException;
import com.example.simple.domain.main.dto.MainPostDTO;
import com.example.simple.domain.main.dto.ReqGetCommentUpdateDTO;
import com.example.simple.domain.main.dto.ReqGetPostUpdateDTO;
import com.example.simple.domain.main.dto.ReqPostDTO;
import com.example.simple.domain.main.dto.ResGetCommentUpdateDataDTO;
import com.example.simple.domain.main.dto.ResGetPostUpdateDTO;
import com.example.simple.domain.main.dto.ResMainPostDTO;
import com.example.simple.domain.main.dto.ResPostDTO;
import com.example.simple.model.comment.entity.CommentEntity;
import com.example.simple.model.comment.repository.CommentRepository;
import com.example.simple.model.post.entity.PostEntity;
import com.example.simple.model.post.repository.PostRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public ResMainPostDTO getPostListData() {

        List<PostEntity> postEntityList = postRepository.findByDeleteDateIsNullOrderByIdxDesc();

        // List<MainPostDTO> mainPostDTOList = new ArrayList<>();

        // for (PostEntity postEntity : postEntityList) {
        // MainPostDTO mainPostDTO = new MainPostDTO(postEntity.getIdx(),
        // postEntity.getTitle(),
        // postEntity.getUserEntity().getId());

        // mainPostDTOList.add(mainPostDTO);
        // }

        // List<MainPostDTO> mainPostDTOList = postEntityList.stream()
        // .map(postEntity -> new MainPostDTO(postEntity.getIdx(),
        // postEntity.getTitle(),
        // postEntity.getUserEntity().getId()))
        // .toList();

        List<MainPostDTO> mainPostDTOList = postEntityList.stream()
                .map(postEntity -> MainPostDTO.builder()
                        .idx(postEntity.getIdx())
                        .title(postEntity.getTitle())
                        .userId(postEntity.getUserEntity().getId())
                        .build())
                .toList();

        return new ResMainPostDTO(mainPostDTOList);

    }

    public ResPostDTO getPostData(Long postIdx) {

        Optional<PostEntity> postEntityOptional = postRepository.findByIdxAndDeleteDateIsNull(postIdx);

        List<CommentEntity> commentEntityList = commentRepository.findByPostEntity_Idx(postIdx);

        // List<ResCommentDTO> resCommentDTOList = new ArrayList<>();

        // for (CommentEntity commentEntity : commentEntityList) {
        // ResCommentDTO resCommentDTO = new ResCommentDTO(commentEntity.getIdx(),
        // commentEntity.getUserEntity().getId(),
        // commentEntity.getContent(), commentEntity.getCreateDate());

        // resCommentDTOList.add(resCommentDTO);
        // }

        // List<ResCommentDTO> resCommentDTOList = commentEntityList.stream()
        // .map(commentEntity -> new ResCommentDTO(commentEntity.getIdx(),
        // commentEntity.getUserEntity().getId(),
        // commentEntity.getContent(), commentEntity.getCreateDate(),
        // commentEntity.getUpdateDate()))
        // .toList();

        if (!postEntityOptional.isPresent()) {
            throw new CustomNotFoundException("존재하지 않은 게시물 입니다");
        }

        PostEntity postEntity = postEntityOptional.get();

        // ReqPostDTO reqPostDTO = new ReqPostDTO(postEntityOptional.get().getIdx(),
        // postEntityOptional.get().getTitle(),
        // postEntityOptional.get().getContent(),
        // postEntityOptional.get().getUserEntity().getId(),
        // postEntityOptional.get().getUpdateDate(),
        // postEntityOptional.get().getCreateDate(), resCommentDTOList);

        ReqPostDTO reqPostDTO = ReqPostDTO.convert(postEntity, commentEntityList);

        return new ResPostDTO(reqPostDTO);

    }

    public ResGetPostUpdateDTO getPostUpdateData(Long postIdx, HttpSession session) {

        LoginDTO loginDTO = (LoginDTO) session.getAttribute("dto");

        if (loginDTO == null) {
            throw new UnauthorizedException("로그인 정보가 없습니다");

        }

        Optional<PostEntity> postEntityOptional = postRepository.findByIdx(postIdx);

        if (!postEntityOptional.isPresent()) {
            throw new CustomNotFoundException("해당 게시물을 찾을 수 없습니다");
        }

        PostEntity postEntity = postEntityOptional.get();

        if (!loginDTO.getUser().getIdx().equals(postEntity.getUserEntity().getIdx())) {
            throw new UnauthorizedException("작성한 게시자가 아닙니다");
        }

        // ReqGetPostUpdateDTO reqGetPostUpdateData = new
        // ReqGetPostUpdateDTO(postEntity.getIdx(), postEntity.getTitle(),
        // postEntity.getContent(), postEntity.getIdx());

        ReqGetPostUpdateDTO reqGetPostUpdateData = ReqGetPostUpdateDTO.builder()
                .idx(postEntity.getIdx())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .userIdx(postEntity.getUserEntity().getIdx())
                .build();

        return new ResGetPostUpdateDTO(reqGetPostUpdateData);

    }

    public ResGetCommentUpdateDataDTO getCommentUpdateData(Long commentIdx, HttpSession session) {

        LoginDTO loginDTO = (LoginDTO) session.getAttribute("dto");

        if (loginDTO == null) {
            throw new UnauthorizedException("로그인 정보가 없습니다");
        }

        Optional<CommentEntity> commentEntityOptional = commentRepository.findByIdx(commentIdx);

        if (!commentEntityOptional.isPresent()) {
            throw new CustomNotFoundException("해당 댓글을 찾을 수 없습니다");
        }

        CommentEntity commentEntity = commentEntityOptional.get();

        if (!commentEntity.getUserEntity().getIdx().equals(loginDTO.getUser().getIdx())) {
            throw new UnauthorizedException("작성한 게시자가 아닙니다");
        }

        // ReqGetCommentUpdateDTO reqGetCommentUpdateDTO = new
        // ReqGetCommentUpdateDTO(commentEntity.getIdx(),
        // commentEntity.getContent(), commentEntity.getPostEntity().getIdx());

        ReqGetCommentUpdateDTO reqGetCommentUpdateDTO = ReqGetCommentUpdateDTO.builder()
                .idx(commentEntity.getIdx())
                .content(commentEntity.getContent())
                .postIdx(commentEntity.getPostEntity().getIdx())
                .build();

        return new ResGetCommentUpdateDataDTO(reqGetCommentUpdateDTO);

    }

}
