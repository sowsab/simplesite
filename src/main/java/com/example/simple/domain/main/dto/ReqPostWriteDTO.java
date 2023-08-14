package com.example.simple.domain.main.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReqPostWriteDTO {
    
    @Valid
    @NotNull(message = "게시글을 입력해주세요")
    private Post post;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Post {
        
        @NotNull(message = "제목을 입력해주세요")
        private String title;

        @NotNull(message = "내용을 입력해주세요")
        private String content;
        
    }

}
