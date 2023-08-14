package com.example.simple.domain.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simple.common.dto.ResponseDTO;
import com.example.simple.domain.auth.dto.ReqJoinDTO;
import com.example.simple.domain.auth.dto.ReqLoginDTO;
import com.example.simple.domain.auth.dto.ReqUpdateDTO;
import com.example.simple.domain.auth.service.AuthServiceApiV1;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthControllerApiV1 {

    private final AuthServiceApiV1 authServiceApiV1;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ReqLoginDTO reqLoginDTO, HttpSession session) {
        return authServiceApiV1.login(reqLoginDTO, session);
    }

    @PostMapping("/join")
    public ResponseEntity<ResponseDTO<?>> join(@Valid @RequestBody ReqJoinDTO dto) {
        return authServiceApiV1.join(dto);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseDTO<?>> update(@RequestBody ReqUpdateDTO dto, HttpSession session) {

        return authServiceApiV1.update(dto, session);
    }

    
}
