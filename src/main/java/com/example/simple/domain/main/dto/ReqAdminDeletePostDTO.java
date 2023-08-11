package com.example.simple.domain.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReqAdminDeletePostDTO {
    
    private Post post;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Post {
    
        private Long idx;
        
    }

}
