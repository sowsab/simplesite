package com.example.simple.domain.auth.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.simple.common.dto.LoginDTO;
import com.example.simple.common.dto.ResponseDTO;
import com.example.simple.common.exception.BadRequestException;
import com.example.simple.common.exception.UnauthorizedException;
import com.example.simple.domain.auth.dto.ReqJoinDTO;
import com.example.simple.domain.auth.dto.ReqLoginDTO;
import com.example.simple.domain.auth.dto.ReqUpdateDTO;
import com.example.simple.model.user.entity.UserEntity;
import com.example.simple.model.user.entity.UserRoleEntity;
import com.example.simple.model.user.repository.UserRepository;
import com.example.simple.model.user.repository.UserRoleRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceApiV1 {

        private final UserRepository userRepository;
        private final UserRoleRepository userRoleRepository;

        public ResponseEntity<?> login(ReqLoginDTO dto, HttpSession session) {
                Optional<UserEntity> userEntityOptional = userRepository.findById(dto.getUser().getId());

                if (!userEntityOptional.isPresent()) {
                        throw new BadRequestException("존재하지 않는 사용자 입니다");
                }

                UserEntity userEntity = userEntityOptional.get();

                if (!userEntity.getPassword().equals(dto.getUser().getPassword())) {
                        throw new BadRequestException("비밀번호가 일치하지 않습니다");
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

                // if (dto.getUser().getEmail() == "" || dto.getUser().getEmail() == null ||
                //                 dto.getUser().getId() == "" || dto.getUser().getId() == null ||
                //                 dto.getUser().getPassword() == "" || dto.getUser().getPassword() == null) {
                //         throw new BadRequestException("회원정보를 확인해주세요");
                // }

                if (!dto.getUser().getPassword().equals(dto.getUser().getCheckpw())) {
                        throw new BadRequestException("비밀번호를 확인해 주세요");
                }

                Optional<UserEntity> userEntityOptional = userRepository.findById(dto.getUser().getId());

                if (userEntityOptional.isPresent()) {
                        throw new BadRequestException("이미 존재하는 아이디 입니다");
                }

                userEntityOptional = userRepository.findByEmail(dto.getUser().getEmail());

                if (userEntityOptional.isPresent()) {
                        throw new BadRequestException("이미 존재하는 이메일 입니다");
                }

                UserEntity userEntity = UserEntity.builder()
                                .email(dto.getUser().getEmail())
                                .id(dto.getUser().getId())
                                .password(dto.getUser().getPassword())
                                .build();

                userRepository.save(userEntity);

                Optional<UserEntity> joinUserEntityOptional = userRepository.findById(dto.getUser().getId());

                UserEntity joinUserEntity = joinUserEntityOptional.get();

                UserRoleEntity userRoleEntity = UserRoleEntity.builder()
                                .userEntity(UserEntity.builder().idx(joinUserEntity.getIdx()).build())
                                .role("USER")
                                .createDate(LocalDateTime.now())
                                .build();

                userRoleRepository.save(userRoleEntity);

                return new ResponseEntity<>(
                                ResponseDTO.builder()
                                                .code(0)
                                                .message("회원가입에 성공했습니다")
                                                .build(),
                                HttpStatus.OK);
        }

        public ResponseEntity<ResponseDTO<?>> update(ReqUpdateDTO dto, HttpSession session) {

                LoginDTO loginDTO = (LoginDTO) session.getAttribute("dto");

                if (loginDTO == null) {
                        throw new UnauthorizedException("로그인 하지 않았습니다");
                }

                if (!dto.getUser().getCheckpw().equals(loginDTO.getUser().getPassword())) {
                        throw new BadRequestException("비밀번호를 확인해주세요");
                }

                if (!dto.getUser().getId().equals(loginDTO.getUser().getId())) {
                        Optional<UserEntity> userEntityOptional = userRepository.findById(dto.getUser().getId());

                        if (userEntityOptional.isPresent()) {
                                throw new BadRequestException("아이디가 중복 되었습니다");
                        }
                }

                if (!dto.getUser().getEmail().equals(loginDTO.getUser().getEmail())) {
                        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(dto.getUser().getEmail());

                        if (userEntityOptional.isPresent()) {
                                throw new BadRequestException("이메일이 중복 되었습니다");
                        }
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
