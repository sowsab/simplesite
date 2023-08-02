package com.example.simple.domain.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.simple.common.dto.LoginDTO;
import com.example.simple.common.dto.ResponseDTO;
import com.example.simple.domain.auth.dto.ReqLoginDTO;
import com.example.simple.model.user.entity.UserEntity;
import com.example.simple.model.user.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class AuthServiceApiV1 {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> login(ReqLoginDTO dto, HttpSession session) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(dto.getUser().getId());

        if (!userEntityOptional.isPresent()) {
            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .code(1)
                            .message("존재하지 않는 사용자 입니다")
                            .build(),
                    HttpStatus.BAD_REQUEST);
        }

        if (dto.getUser().getId() == null ||
                dto.getUser().getId().equals("")) {
            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .code(1)
                            .message("아이디를 입력해 주세요")
                            .build(),
                    HttpStatus.BAD_REQUEST);
        }

        UserEntity userEntity = userEntityOptional.get();

        if (!userEntity.getPassword().equals(dto.getUser().getPassword())) {
            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .code(1)
                            .message("비밀번호가 일치하지 않습니다")
                            .build(),
                    HttpStatus.BAD_REQUEST);
        }

        session.setAttribute("dto", LoginDTO.Convert(userEntity));

        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .code(0)
                        .message("로그인에 성공했습니다")
                        .build(),
                HttpStatus.OK);

    }

}
