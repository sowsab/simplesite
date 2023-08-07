package com.example.simple.domain.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReqCommentUpdateDTO {
    
    private Comment comment;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Comment {
    
        private Long idx;
        private String content;
        
    }

}
