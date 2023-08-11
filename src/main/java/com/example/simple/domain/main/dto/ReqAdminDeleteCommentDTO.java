package com.example.simple.domain.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReqAdminDeleteCommentDTO {
    
    private Comment comment;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Comment {
    
        private Long idx;
        
    }

}
