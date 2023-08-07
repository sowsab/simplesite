package com.example.simple.domain.auth.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.simple.common.dto.LoginDTO;
import com.example.simple.domain.auth.dto.ResGetUpdateDTO;
import com.example.simple.domain.auth.dto.ResGetUpdateDTO.ReqGetUpdateDataDTO;
import com.example.simple.model.user.entity.UserEntity;
import com.example.simple.model.user.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public ResGetUpdateDTO getUpdateUser(HttpSession session) {
                LoginDTO sessionDTO = (LoginDTO) session.getAttribute("dto");
                
                Optional<UserEntity> userEntityOptional = userRepository.findByIdx(sessionDTO.getUser().getIdx());

                UserEntity userEntity = userEntityOptional.get();

                ReqGetUpdateDataDTO reqGetUpdateDataDTO = new ReqGetUpdateDataDTO(userEntity.getId(), userEntity.getEmail());

                return new ResGetUpdateDTO(reqGetUpdateDataDTO);

        }
    
}
