package com.example.simple.domain.main.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simple.common.dto.ResponseDTO;
import com.example.simple.domain.main.dto.ReqAdminDeleteCommentDTO;
import com.example.simple.domain.main.dto.ReqAdminDeletePostDTO;
import com.example.simple.domain.main.dto.ReqCommentDeleteDTO;
import com.example.simple.domain.main.dto.ReqCommentUpdateDTO;
import com.example.simple.domain.main.dto.ReqCommentWriteDTO;
import com.example.simple.domain.main.dto.ReqDeletePostDTO;
import com.example.simple.domain.main.dto.ReqPostUpdateDTO;
import com.example.simple.domain.main.dto.ReqPostWriteDTO;
import com.example.simple.domain.main.service.MainServiceApiV1;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class MainControllerApiV1 {
    
    private final MainServiceApiV1 mainServiceApiV1;

    @PostMapping("/write")
    public ResponseEntity<ResponseDTO<?>> postWrite(@RequestBody @Valid ReqPostWriteDTO dto, HttpSession session) {
        return mainServiceApiV1.postWrite(dto, session);
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseDTO<?>> postDelete(@RequestBody ReqDeletePostDTO dto, HttpSession session) {
        return mainServiceApiV1.deletePost(dto, session);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseDTO<?>> postUpdate(@RequestBody @Valid ReqPostUpdateDTO dto, HttpSession session) {
        return mainServiceApiV1.postUpdate(dto, session);
    }

    @PostMapping("/comment/write")
    public ResponseEntity<ResponseDTO<?>> commentWrite(@RequestBody @Valid ReqCommentWriteDTO dto, HttpSession session) {
        return mainServiceApiV1.commentWrite(dto, session);
    }

    @PostMapping("/comment/delete")
    public ResponseEntity<ResponseDTO<?>> commentDelete(@RequestBody ReqCommentDeleteDTO dto, HttpSession session) {
        return mainServiceApiV1.deleteComment(dto, session);
    }

    @PostMapping("/comment/update")
    public ResponseEntity<ResponseDTO<?>> commentUpdate(@RequestBody @Valid ReqCommentUpdateDTO dto, HttpSession session) {
        return mainServiceApiV1.commentUpdate(dto, session);
    }

    @PostMapping("/admin/delete")
    public ResponseEntity<ResponseDTO<?>> adminDeletePost(@RequestBody ReqAdminDeletePostDTO dto, HttpSession session) {
        return mainServiceApiV1.adminDeletePost(dto, session);
    }

    @PostMapping("/admin/comment/delete")
    public ResponseEntity<ResponseDTO<?>> adminDeleteComment(@RequestBody ReqAdminDeleteCommentDTO dto, HttpSession session) {
        return mainServiceApiV1.adminCommentDelete(dto, session);
    }

}
