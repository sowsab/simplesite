package com.example.simple.domain.main.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.simple.model.comment.entity.CommentEntity;
import com.example.simple.model.post.entity.PostEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ResPostDTO {

    private ReqPostDTO reqPostDTO;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class ReqPostDTO {

        private Long idx;
        private String title;
        private String content;
        private Long userIdx;
        private String userId;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;

        private List<ResCommentDTO> resCommentDTOList;

        public static ReqPostDTO convert(PostEntity postEntity, List<CommentEntity> commentEntityList) {
            return ReqPostDTO.builder()
                    .idx(postEntity.getIdx())
                    .title(postEntity.getTitle())
                    .content(postEntity.getContent())
                    .userIdx(postEntity.getUserEntity().getIdx())
                    .userId(postEntity.getUserEntity().getId())
                    .createDate(postEntity.getCreateDate())
                    .updateDate(postEntity.getUpdateDate())
                    .resCommentDTOList(commentEntityList.stream()
                            .map(commentEntity -> ResCommentDTO.convert(commentEntity))
                            .toList())
                    .build();
        }

        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        @Builder
        public static class ResCommentDTO {

            private Long idx;
            private Long userIdx;
            private String userId;
            private String content;
            private LocalDateTime createDate;
            private LocalDateTime updateDate;

            public static ResCommentDTO convert(CommentEntity commentEntity) {
                return ResCommentDTO.builder()
                        .idx(commentEntity.getIdx())
                        .content(commentEntity.getContent())
                        .userIdx(commentEntity.getUserEntity().getIdx())
                        .userId(commentEntity.getUserEntity().getId())
                        .createDate(commentEntity.getCreateDate())
                        .updateDate(commentEntity.getUpdateDate())
                        .build();
            }

        }

    }

}
