package com.example.simple.domain.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.simple.common.dto.LoginDTO;
import com.example.simple.domain.auth.dto.ResAdminGetUserDTO;
import com.example.simple.domain.auth.dto.ResAdminGetUserDetailDTO;
import com.example.simple.domain.auth.dto.ResGetPostCommentDTO;
import com.example.simple.domain.auth.dto.ResGetUpdateDTO;
import com.example.simple.domain.auth.dto.ResGetUpdateDTO.ReqGetUpdateDataDTO;
import com.example.simple.model.comment.entity.CommentEntity;
import com.example.simple.model.comment.repository.CommentRepository;
import com.example.simple.model.post.entity.PostEntity;
import com.example.simple.model.post.repository.PostRepository;
import com.example.simple.model.user.entity.UserEntity;
import com.example.simple.model.user.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

        private final UserRepository userRepository;
        private final PostRepository postRepository;
        private final CommentRepository commentRepository;

        public ResGetUpdateDTO getUpdateUser(HttpSession session) {
                LoginDTO sessionDTO = (LoginDTO) session.getAttribute("dto");

                Optional<UserEntity> userEntityOptional = userRepository.findByIdx(sessionDTO.getUser().getIdx());

                UserEntity userEntity = userEntityOptional.get();

                ReqGetUpdateDataDTO reqGetUpdateDataDTO = new ReqGetUpdateDataDTO(userEntity.getId(),
                                userEntity.getEmail());

                return new ResGetUpdateDTO(reqGetUpdateDataDTO);

        }

        public ResGetPostCommentDTO getUserPostComment(HttpSession session) {

                LoginDTO loginDTO = (LoginDTO) session.getAttribute("dto");

                List<PostEntity> postEntityList = postRepository.findByUserEntity_Idx(loginDTO.getUser().getIdx());

                List<CommentEntity> commentEntityList = commentRepository
                                .findByUserEntity_Idx(loginDTO.getUser().getIdx());

                ResGetPostCommentDTO dto = ResGetPostCommentDTO.convert(postEntityList, commentEntityList);

                return dto;

        }

        public ResAdminGetUserDTO adminGetUser(HttpSession session) {
                LoginDTO loginDTO = (LoginDTO) session.getAttribute("dto");

                if (!loginDTO.getUser().getRoleList().contains("ADMIN")) {
                        throw new RuntimeException("관리자 권한이 없습니다");
                }

                List<UserEntity> userEntityList = userRepository.findAll();

                ResAdminGetUserDTO dto = ResAdminGetUserDTO.convert(userEntityList);

                return dto;

        }

        public ResAdminGetUserDetailDTO adminGetUserDetail(Long userIdx, HttpSession session) {

                LoginDTO loginDTO = (LoginDTO) session.getAttribute("dto");

                if (!loginDTO.getUser().getRoleList().contains("ADMIN")) {
                        throw new RuntimeException("관리자 권한이 없습니다");
                }

                List<PostEntity> postEntityList = postRepository.findByUserEntity_Idx(userIdx);

                List<CommentEntity> commentEntityList = commentRepository.findByUserEntity_Idx(userIdx);

                ResAdminGetUserDetailDTO dto = ResAdminGetUserDetailDTO.convert(postEntityList, commentEntityList);

                return dto;
        }

}
