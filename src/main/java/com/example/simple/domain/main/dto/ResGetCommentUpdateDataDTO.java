package com.example.simple.domain.main.dto;

import com.example.simple.model.comment.entity.CommentEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ResGetCommentUpdateDataDTO {

    private ReqGetCommentUpdateDTO reqGetCommentUpdateDTO;

    public static ResGetCommentUpdateDataDTO convert(CommentEntity commentEntity) {
        return ResGetCommentUpdateDataDTO.builder()
        .reqGetCommentUpdateDTO(ReqGetCommentUpdateDTO.convert(
                        commentEntity))
        .build();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class ReqGetCommentUpdateDTO {

        private Long idx;
        private String content;
        private Long postIdx;

        public static ReqGetCommentUpdateDTO convert(CommentEntity commentEntity) {
            return ReqGetCommentUpdateDTO.builder()
            .idx(commentEntity.getIdx())
            .content(commentEntity.getContent())
            .postIdx(commentEntity.getPostEntity().getIdx())
            .build();
        }

    }
}
