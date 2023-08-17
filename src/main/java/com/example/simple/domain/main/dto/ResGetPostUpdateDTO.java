package com.example.simple.domain.main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResGetPostUpdateDTO {

    private ReqGetPostUpdateDTO reqGetPostUpdateDTO;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class ReqGetPostUpdateDTO {

        private Long idx;
        private String title;
        private String content;
        private Long userIdx;

    }

}
