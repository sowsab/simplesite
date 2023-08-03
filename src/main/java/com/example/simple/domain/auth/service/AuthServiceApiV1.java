package com.example.simple.domain.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.simple.common.dto.LoginDTO;
import com.example.simple.common.dto.ResponseDTO;
import com.example.simple.domain.auth.dto.ReqJoinDTO;
import com.example.simple.domain.auth.dto.ReqLoginDTO;
import com.example.simple.domain.auth.dto.ReqUpdateDTO;
import com.example.simple.domain.auth.dto.ResUpdateDTO;
import com.example.simple.model.user.entity.UserEntity;
import com.example.simple.model.user.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class AuthServiceApiV1 {

        @Autowired
        private UserRepository userRepository;

        public ResUpdateDTO getUpdateUser(HttpSession session) {
                LoginDTO sessionDTO = (LoginDTO) session.getAttribute("dto");
                
                Optional<UserEntity> userEntityOptional = userRepository.findByIdx(sessionDTO.getUser().getIdx());

                UserEntity userEntity = userEntityOptional.get();

                return new ResUpdateDTO(userEntity.getId(), userEntity.getEmail());

        }

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

        public ResponseEntity<ResponseDTO<?>> join(ReqJoinDTO dto) {

                if (dto.getUser().getEmail() == "" || dto.getUser().getEmail() == null ||
                dto.getUser().getId() == "" || dto.getUser().getId() == null ||
                dto.getUser().getPassword() == "" || dto.getUser().getPassword() == null) {
                        return new ResponseEntity<>(
                                        ResponseDTO.builder()
                                                        .code(1)
                                                        .message("회원정보를 확인해주세요")
                                                        .build(),
                                        HttpStatus.BAD_REQUEST);
                }

                Optional<UserEntity> userEntityOptional = userRepository.findById(dto.getUser().getId());

                if (userEntityOptional.isPresent()) {
                        return new ResponseEntity<>(
                                        ResponseDTO.builder()
                                                        .code(1)
                                                        .message("이미 존재하는 아이디 입니다")
                                                        .build(),
                                        HttpStatus.BAD_REQUEST);
                }

                userEntityOptional = userRepository.findByEmail(dto.getUser().getEmail());

                if (userEntityOptional.isPresent()) {
                        return new ResponseEntity<>(
                                        ResponseDTO.builder()
                                                        .code(1)
                                                        .message("이미 존재하는 이메일 입니다")
                                                        .build(),
                                        HttpStatus.BAD_REQUEST);
                }

                UserEntity userEntity = UserEntity.builder()
                .email(dto.getUser().getEmail())
                .id(dto.getUser().getId())
                .password(dto.getUser().getPassword())
                .build();

                userRepository.save(userEntity);

                return new ResponseEntity<>(
                                ResponseDTO.builder()
                                                .code(0)
                                                .message("회원가입에 성공했습니다")
                                                .build(),
                                HttpStatus.OK);
        }

        public ResponseEntity<ResponseDTO<?>> update(ReqUpdateDTO dto, HttpSession session) {

                LoginDTO loginDTO = (LoginDTO) session.getAttribute("dto");

                if (dto.getUser().getEmail() == "" || dto.getUser().getEmail() == null ||
                dto.getUser().getId() == "" || dto.getUser().getId() == null ||
                dto.getUser().getPassword() == "" || dto.getUser().getPassword() == null) {
                        return new ResponseEntity<>(
                                        ResponseDTO.builder()
                                                        .code(1)
                                                        .message("회원정보를 확인해주세요")
                                                        .build(),
                                        HttpStatus.BAD_REQUEST);
                }

                if (!dto.getUser().getCheckPw().equals(loginDTO.getUser().getPassword())) {
                        return new ResponseEntity<>(
                                        ResponseDTO.builder()
                                                        .code(1)
                                                        .message("비밀번호를 확인해주세요")
                                                        .build(),
                                        HttpStatus.BAD_REQUEST);
                }

                Optional<UserEntity> userEntityOptional = userRepository.findById(dto.getUser().getId());

                if (userEntityOptional.isPresent()) {
                        return new ResponseEntity<>(
                                        ResponseDTO.builder()
                                                        .code(1)
                                                        .message("아이디가 중복 되었습니다")
                                                        .build(),
                                        HttpStatus.BAD_REQUEST);
                }

                userEntityOptional = userRepository.findByEmail(dto.getUser().getEmail());

                if (userEntityOptional.isPresent()) {
                        return new ResponseEntity<>(
                                        ResponseDTO.builder()
                                                        .code(1)
                                                        .message("이메일이 중복 되었습니다")
                                                        .build(),
                                        HttpStatus.BAD_REQUEST);
                }

                UserEntity userEntity = UserEntity.builder()
                .idx(loginDTO.getUser().getIdx())
                .id(dto.getUser().getId())
                .email(dto.getUser().getEmail())
                .password(dto.getUser().getPassword())
                .build();

                userRepository.save(userEntity);
                
                return new ResponseEntity<>(
                                ResponseDTO.builder()
                                                .code(0)
                                                .message("회원정보 수정에 성공했습니다")
                                                .build(),
                                HttpStatus.OK);

        }
        


}
