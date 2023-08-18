package com.example.simple.domain.main.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.simple.model.post.entity.PostEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ResMainPostDTO {

    private List<MainPostDTO> mainPostDTOList;

    public static ResMainPostDTO convert(List<PostEntity> postEntityList) {
        return ResMainPostDTO.builder()
                .mainPostDTOList(postEntityList.stream()
                        .map(postEntity -> MainPostDTO.convert(postEntity))
                        .toList())
                .build();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class MainPostDTO {

        private Long idx;
        private String title;
        private Long userIdx;
        private String userId;
        private LocalDateTime createDate;

        public static MainPostDTO convert(PostEntity postEntity) {
            return MainPostDTO.builder()
                    .idx(postEntity.getIdx())
                    .title(postEntity.getTitle())
                    .userIdx(postEntity.getUserEntity().getIdx())
                    .userId(postEntity.getUserEntity().getId())
                    .createDate(postEntity.getCreateDate())
                    .build();
        }

    }

}
