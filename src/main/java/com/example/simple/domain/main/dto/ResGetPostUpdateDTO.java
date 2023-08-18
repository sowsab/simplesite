package com.example.simple.domain.main.dto;

import com.example.simple.model.post.entity.PostEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ResGetPostUpdateDTO {

    private ReqGetPostUpdateDTO reqGetPostUpdateDTO;

    public static ResGetPostUpdateDTO convert(PostEntity postEntity) {
        return ResGetPostUpdateDTO.builder()
        .reqGetPostUpdateDTO(ReqGetPostUpdateDTO.convert(postEntity))
        .build();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class ReqGetPostUpdateDTO {

        private Long idx;
        private String title;
        private String content;
        private Long userIdx;

        public static ReqGetPostUpdateDTO convert(PostEntity postEntity) {
            return ReqGetPostUpdateDTO.builder()
                    .idx(postEntity.getIdx())
                    .title(postEntity.getTitle())
                    .content(postEntity.getContent())
                    .userIdx(postEntity.getUserEntity().getIdx())
                    .build();
        }

    }

}
