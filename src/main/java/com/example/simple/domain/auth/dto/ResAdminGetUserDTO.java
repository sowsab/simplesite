package com.example.simple.domain.auth.dto;

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
public class ResAdminGetUserDTO {

    private List<User> userList;

    public static ResAdminGetUserDTO convert(List<UserEntity> userEntitityList) {
        return ResAdminGetUserDTO.builder()
                .userList(userEntitityList.stream()
                        .map(userEntity -> User.convert(userEntity))
                        .toList())
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
        private List<String> roleList;

        public static User convert(UserEntity userEntity) {
            return User.builder()
                    .idx(userEntity.getIdx())
                    .email(userEntity.getEmail())
                    .id(userEntity.getId())
                    .roleList(
                            userEntity.getUserRoleEntitiyList().stream()
                                    .map(userRoleEntity -> userRoleEntity.getRole())
                                    .toList())
                    .build();
        }
    }

}
