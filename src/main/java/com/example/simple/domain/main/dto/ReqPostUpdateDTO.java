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
public class ReqPostUpdateDTO {
    
    @Valid
    @NotNull(message = "게시글을 입력해주세요")
    private Post post;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Post {
    
        @NotNull
        private Long idx;

        @NotEmpty(message = "제목을 입력해주세요")
        private String title;

        @NotEmpty(message = "내용을 입력해주세요")
        private String content;
        
    }

}
