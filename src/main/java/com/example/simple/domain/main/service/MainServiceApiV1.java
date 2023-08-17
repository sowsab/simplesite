package com.example.simple.domain.main.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.simple.common.dto.LoginDTO;
import com.example.simple.common.dto.ResponseDTO;
import com.example.simple.common.exception.CustomNotFoundException;
import com.example.simple.common.exception.UnauthorizedException;
import com.example.simple.domain.main.dto.ReqAdminDeleteCommentDTO;
import com.example.simple.domain.main.dto.ReqAdminDeletePostDTO;
import com.example.simple.domain.main.dto.ReqCommentDeleteDTO;
import com.example.simple.domain.main.dto.ReqCommentUpdateDTO;
import com.example.simple.domain.main.dto.ReqCommentWriteDTO;
import com.example.simple.domain.main.dto.ReqDeletePostDTO;
import com.example.simple.domain.main.dto.ReqPostUpdateDTO;
import com.example.simple.domain.main.dto.ReqPostWriteDTO;
import com.example.simple.model.comment.entity.CommentEntity;
import com.example.simple.model.comment.repository.CommentRepository;
import com.example.simple.model.post.entity.PostEntity;
import com.example.simple.model.post.repository.PostRepository;
import com.example.simple.model.user.entity.UserEntity;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainServiceApiV1 {

        private final PostRepository postRepository;
        private final CommentRepository commentRepository;

        public ResponseEntity<ResponseDTO<?>> commentUpdate(ReqCommentUpdateDTO dto, HttpSession session) {

                LoginDTO loginDTO = (LoginDTO) session.getAttribute("dto");

                Optional<CommentEntity> commentEntityOptional = commentRepository.findByIdx(dto.getComment().getIdx());

                if (!commentEntityOptional.isPresent()) {
                        throw new CustomNotFoundException("존재하지 않는 댓글입니다");
                }

                CommentEntity commentEntity = commentEntityOptional.get();

                if (!commentEntity.getUserEntity().getIdx().equals(loginDTO.getUser().getIdx())) {
                        throw new UnauthorizedException("댓글을 쓴 사람이 아닙니다");
                }

                CommentEntity updateCommentEntity = CommentEntity.builder()
                                .idx(commentEntity.getIdx())
                                .content(dto.getComment().getContent())
                                .postEntity(PostEntity.builder()
                                                .idx(commentEntity.getPostEntity().getIdx())
                                                .build())
                                .userEntity(UserEntity.builder()
                                                .idx(commentEntity.getUserEntity().getIdx())
                                                .build())
                                .createDate(commentEntity.getCreateDate())
                                .updateDate(LocalDateTime.now())
                                .build();

                commentRepository.save(updateCommentEntity);

                return new ResponseEntity<>(
                                ResponseDTO.builder()
                                                .code(0)
                                                .message("댓글 수정에 성공했습니다")
                                                .build(),
                                HttpStatus.OK);

        }

        public ResponseEntity<ResponseDTO<?>> commentWrite(ReqCommentWriteDTO dto, HttpSession session) {

                LoginDTO loginDTO = (LoginDTO) session.getAttribute("dto");

                CommentEntity commentEntity = CommentEntity.builder()
                                .content(dto.getComment().getContent())
                                .postEntity(PostEntity
                                                .builder()
                                                .idx(dto.getComment().getPostIdx())
                                                .build())
                                .userEntity(UserEntity
                                                .builder()
                                                .idx(loginDTO.getUser().getIdx())
                                                .build())
                                .createDate(LocalDateTime.now())
                                .build();

                commentRepository.save(commentEntity);

                return new ResponseEntity<>(
                                ResponseDTO.builder()
                                                .code(0)
                                                .message("댓글 게시에 성공했습니다")
                                                .build(),
                                HttpStatus.OK);
        }

        public ResponseEntity<ResponseDTO<?>> postWrite(ReqPostWriteDTO dto, HttpSession session) {

                LoginDTO loginDTO = (LoginDTO) session.getAttribute("dto");

                PostEntity postEntity = PostEntity.builder()
                                .title(dto.getPost().getTitle())
                                .content(dto.getPost().getContent())
                                .userEntity(UserEntity.builder()
                                                .idx(loginDTO.getUser().getIdx())
                                                .build())
                                .createDate(LocalDateTime.now())
                                .build();

                postRepository.save(postEntity);

                return new ResponseEntity<>(
                                ResponseDTO.builder()
                                                .code(0)
                                                .message("게시글 게시에 성공했습니다")
                                                .build(),
                                HttpStatus.OK);
        }

        public ResponseEntity<ResponseDTO<?>> postUpdate(ReqPostUpdateDTO dto, HttpSession session) {

                Optional<PostEntity> postEntityOptional = postRepository.findByIdx(dto.getPost().getIdx());

                if (!postEntityOptional.isPresent()) {
                        throw new CustomNotFoundException("존재하지 않는 게시글 입니다");
                }

                LoginDTO loginDTO = (LoginDTO) session.getAttribute("dto");

                PostEntity postEntity = postEntityOptional.get();

                if (!loginDTO.getUser().getIdx().equals(postEntity.getUserEntity().getIdx())) {
                        throw new UnauthorizedException("작성자가 아닙니다");
                }

                PostEntity updateEntity = PostEntity.builder()
                                .idx(postEntity.getIdx())
                                .title(dto.getPost().getTitle())
                                .content(dto.getPost().getContent())
                                .createDate(postEntity.getCreateDate())
                                .updateDate(LocalDateTime.now())
                                .build();

                postRepository.save(updateEntity);

                return new ResponseEntity<>(
                                ResponseDTO.builder()
                                                .code(0)
                                                .message("게시글 수정에 성공했습니다")
                                                .build(),
                                HttpStatus.OK);
        }

        public ResponseEntity<ResponseDTO<?>> deletePost(ReqDeletePostDTO dto, HttpSession session) {

                Optional<PostEntity> postEntityOptional = postRepository
                                .findByIdxAndDeleteDateIsNull(dto.getPost().getIdx());

                if (!postEntityOptional.isPresent()) {
                        throw new CustomNotFoundException("존재하지 않는 게시글 입니다");
                }

                PostEntity postEntity = postEntityOptional.get();

                LoginDTO loginDTO = (LoginDTO) session.getAttribute("dto");

                if (loginDTO == null || loginDTO.getUser() == null
                                || !loginDTO.getUser().getIdx().equals(postEntity.getUserEntity().getIdx())) {
                        throw new UnauthorizedException("이 글을 쓴 사람이 아닙니다");
                }

                postEntity.setDeleteDate(LocalDateTime.now());

                postRepository.delete(postEntity);

                return new ResponseEntity<>(
                                ResponseDTO.builder()
                                                .code(0)
                                                .message("게시글 삭제에 성공했습니다")
                                                .build(),
                                HttpStatus.OK);

        }

        public ResponseEntity<ResponseDTO<?>> deleteComment(ReqCommentDeleteDTO dto, HttpSession session) {

                LoginDTO loginDTO = (LoginDTO) session.getAttribute("dto");

                if (loginDTO == null) {
                        throw new UnauthorizedException("로그인을 하지 않았습니다");
                }

                Optional<CommentEntity> commentEntityOptional = commentRepository.findByIdx(dto.getComment().getIdx());
                CommentEntity commentEntity = commentEntityOptional.get();

                if (!commentEntity.getUserEntity().getIdx().equals(loginDTO.getUser().getIdx())) {
                        throw new UnauthorizedException("이 댓글을 쓴 사람이 아닙니다");
                }
                commentEntity.setDeleteDate(LocalDateTime.now());

                commentRepository.delete(commentEntity);

                return new ResponseEntity<>(
                                ResponseDTO.builder()
                                                .code(0)
                                                .message("댓글 삭제에 성공했습니다")
                                                .build(),
                                HttpStatus.OK);
        }

        public ResponseEntity<ResponseDTO<?>> adminDeletePost(ReqAdminDeletePostDTO dto, HttpSession session) {

                LoginDTO loginDTO = (LoginDTO) session.getAttribute("dto");

                if (loginDTO == null) {
                        throw new UnauthorizedException("로그인을 하지 않았습니다");
                }

                if (!loginDTO.getUser().getRoleList().contains("ADMIN")) {
                        throw new UnauthorizedException("관리자가 아닙니다");
                }

                Optional<PostEntity> postEntityOptional = postRepository
                                .findByIdxAndDeleteDateIsNull(dto.getPost().getIdx());
                
                if (!postEntityOptional.isPresent()) {
                        throw new CustomNotFoundException("존재하지 않는 게시글 입니다");
                }

                PostEntity postEntity = postEntityOptional.get();

                postEntity.setDeleteDate(LocalDateTime.now());

                postRepository.delete(postEntity);

                return new ResponseEntity<>(
                                ResponseDTO.builder()
                                                .code(0)
                                                .message("게시글 삭제에 성공했습니다")
                                                .build(),
                                HttpStatus.OK);

        }

        public ResponseEntity<ResponseDTO<?>> adminCommentDelete(ReqAdminDeleteCommentDTO dto, HttpSession session) {

                LoginDTO loginDTO = (LoginDTO) session.getAttribute("dto");

                if (loginDTO == null) {
                        throw new UnauthorizedException("로그인을 하지 않았습니다");
                }

                if (!loginDTO.getUser().getRoleList().contains("ADMIN")) {
                        throw new UnauthorizedException("관리자가 아닙니다");
                }

                Optional<CommentEntity> commentEntityOptional = commentRepository.findByIdx(dto.getComment().getIdx());

                if (!commentEntityOptional.isPresent()) {
                        throw new CustomNotFoundException("존재하지 않는 댓글 입니다");
                }

                CommentEntity commentEntity = commentEntityOptional.get();

                commentEntity.setDeleteDate(LocalDateTime.now());

                commentRepository.delete(commentEntity);

                return new ResponseEntity<>(
                                ResponseDTO.builder()
                                                .code(0)
                                                .message("댓글 삭제에 성공했습니다")
                                                .build(),
                                HttpStatus.OK);
        }

}
