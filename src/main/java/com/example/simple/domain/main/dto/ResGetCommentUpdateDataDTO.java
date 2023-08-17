package com.example.simple.domain.main.dto;

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

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class ReqGetCommentUpdateDTO {

        private Long idx;
        private String content;
        private Long postIdx;

    }
}
