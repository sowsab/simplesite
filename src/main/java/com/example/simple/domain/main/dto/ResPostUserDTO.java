package com.example.simple.domain.main.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.simple.model.post.entity.PostEntity;
import com.example.simple.model.user.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ResPostUserDTO {

    private String userId;
    private List<ReqPostUserDTO> reqPostUserDTOList;

    public static ResPostUserDTO convert(UserEntity userEntity, List<PostEntity> postEntityList) {
        return ResPostUserDTO.builder()
                .userId(userEntity.getId())
                .reqPostUserDTOList(
                        postEntityList.stream()
                                .map(postEntity -> ReqPostUserDTO.convert(postEntity))
                                .toList())
                .build();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class ReqPostUserDTO {
        private Long idx;
        private String title;
        private LocalDateTime createDate;
        private String userId;

        public static ReqPostUserDTO convert(PostEntity postEntity) {
            return ReqPostUserDTO.builder()
                    .idx(postEntity.getIdx())
                    .title(postEntity.getTitle())
                    .createDate(postEntity.getCreateDate())
                    .userId(postEntity.getUserEntity().getId())
                    .build();
        }
    }
}
