package com.example.simple.domain.main.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ReqPostDTO {

    private Long idx;
    private String title;
    private String content;
    private String userId;
    private LocalDateTime createDate;

    private List<ResCommentDTO> resCommentDTOList;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class ResCommentDTO {

        private Long idx;
        private String userId;
        private String content;
        private LocalDateTime createDate;

    }

}
