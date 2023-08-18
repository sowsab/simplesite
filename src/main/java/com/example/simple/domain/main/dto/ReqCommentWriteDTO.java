package com.example.simple.domain.main.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReqCommentWriteDTO {
    
    @Valid
    @NotNull(message = "댓글을 입력해주세요")
    private Comment comment;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Comment {
        
        @NotEmpty(message = "내용을 입력해주세요")
        private String content;

        @NotNull
        private Long postIdx;

    }

}
