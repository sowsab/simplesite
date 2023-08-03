package com.example.simple.domain.main.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simple.common.dto.ResponseDTO;
import com.example.simple.domain.main.dto.ReqPostWriteDTO;
import com.example.simple.domain.main.service.MainServiceApiV1;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class MainControllerApiV1 {
    
    private final MainServiceApiV1 mainServiceApiV1;

    @PostMapping("/write")
    public ResponseEntity<ResponseDTO<?>> postWrite(@RequestBody ReqPostWriteDTO dto, HttpSession session) {
        return mainServiceApiV1.postWrite(dto, session);
    }

}
