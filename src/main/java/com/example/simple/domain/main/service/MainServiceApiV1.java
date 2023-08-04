package com.example.simple.domain.main.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.simple.common.dto.LoginDTO;
import com.example.simple.common.dto.ResponseDTO;
import com.example.simple.domain.main.dto.ReqDeletePostDTO;
import com.example.simple.domain.main.dto.ReqPostUpdateDTO;
import com.example.simple.domain.main.dto.ReqPostWriteDTO;
import com.example.simple.model.post.entity.PostEntity;
import com.example.simple.model.post.repository.PostRepository;
import com.example.simple.model.user.entity.UserEntity;

import jakarta.servlet.http.HttpSession;

@Service
public class MainServiceApiV1 {

        @Autowired
        private PostRepository postRepository;

        public ResponseEntity<ResponseDTO<?>> postWrite(ReqPostWriteDTO dto, HttpSession session) {

                if (dto.getPost().getTitle() == null || dto.getPost().getTitle() == "" ||
                                dto.getPost().getContent() == null || dto.getPost().getContent() == "") {
                        return new ResponseEntity<>(
                                        ResponseDTO.builder()
                                                        .code(1)
                                                        .message("게시글 정보를 확인해주세요")
                                                        .build(),
                                        HttpStatus.BAD_REQUEST);
                }

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
                        return new ResponseEntity<>(
                                        ResponseDTO.builder()
                                                        .code(1)
                                                        .message("존재하지 않는 게시글 입니다")
                                                        .build(),
                                        HttpStatus.BAD_REQUEST);
                }

                if (dto.getPost().getTitle() == null || dto.getPost().getTitle() == "" ||
                                dto.getPost().getContent() == null || dto.getPost().getContent() == "") {
                        return new ResponseEntity<>(
                                        ResponseDTO.builder()
                                                        .code(1)
                                                        .message("게시글 정보를 확인해주세요")
                                                        .build(),
                                        HttpStatus.BAD_REQUEST);
                }

                LoginDTO loginDTO = (LoginDTO) session.getAttribute("dto");

                PostEntity postEntity = postEntityOptional.get();

                if (!loginDTO.getUser().getIdx().equals(postEntity.getUserEntity().getIdx())) {
                        return new ResponseEntity<>(
                                        ResponseDTO.builder()
                                                        .code(1)
                                                        .message("작성자가 아닙니다")
                                                        .build(),
                                        HttpStatus.BAD_REQUEST);
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
                        return new ResponseEntity<>(
                                        ResponseDTO.builder()
                                                        .code(1)
                                                        .message("존재하지 않는 게시글 입니다")
                                                        .build(),
                                        HttpStatus.BAD_REQUEST);
                }

                PostEntity postEntity = postEntityOptional.get();

                LoginDTO loginDTO = (LoginDTO) session.getAttribute("dto");

                if (loginDTO == null || loginDTO.getUser() == null
                                || !loginDTO.getUser().getIdx().equals(postEntity.getUserEntity().getIdx())) {
                        return new ResponseEntity<>(
                                        ResponseDTO.builder()
                                                        .code(1)
                                                        .message("이 글을 쓴 사람이 아닙니다")
                                                        .build(),
                                        HttpStatus.BAD_REQUEST);
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

}
