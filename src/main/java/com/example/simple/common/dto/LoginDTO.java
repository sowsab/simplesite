package com.example.simple.common.dto;

import java.util.List;

import com.example.simple.model.user.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class LoginDTO {
    private User user;

    public static LoginDTO Convert(UserEntity userEntity) {
        return LoginDTO.builder()
                .user(User.entityConvert(userEntity))
                .build();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class User {
        private Long idx;
        private String email;
        private String id;
        private String password;
        private List<String> roleList;

        public static User entityConvert(UserEntity userEntity) {
            return User.builder()
                    .idx(userEntity.getIdx())
                    .email(userEntity.getEmail())
                    .id(userEntity.getId())
                    .password(userEntity.getPassword())
                    .roleList(userEntity.getUserRoleEntitiyList().stream()
                            .map(userRoleEntity -> userRoleEntity.getRole()).toList())
                    .build();
        }
    }
}
