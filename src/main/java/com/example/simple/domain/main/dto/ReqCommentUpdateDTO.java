package com.example.simple.domain.main.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReqCommentUpdateDTO {
    
    @Valid
    @NotNull(message = "댓글을 입력해주세요")
    private Comment comment;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Comment {
    
        @NotNull
        private Long idx;

        @NotNull(message = "내용을 입력해주세요")
        private String content;
        
    }

}
