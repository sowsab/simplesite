package com.example.simple.domain.main.service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.simple.common.dto.LoginDTO;
import com.example.simple.common.dto.ResponseDTO;
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

}
