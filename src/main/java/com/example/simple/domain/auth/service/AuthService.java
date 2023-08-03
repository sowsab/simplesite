package com.example.simple.domain.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.simple.common.dto.LoginDTO;
import com.example.simple.domain.auth.dto.ResUpdateDTO;
import com.example.simple.model.user.entity.UserEntity;
import com.example.simple.model.user.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public ResUpdateDTO getUpdateUser(HttpSession session) {
                LoginDTO sessionDTO = (LoginDTO) session.getAttribute("dto");
                
                Optional<UserEntity> userEntityOptional = userRepository.findByIdx(sessionDTO.getUser().getIdx());

                UserEntity userEntity = userEntityOptional.get();

                return new ResUpdateDTO(userEntity.getId(), userEntity.getEmail());

        }
    
}
