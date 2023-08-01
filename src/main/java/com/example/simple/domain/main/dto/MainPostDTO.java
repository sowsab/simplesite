package com.example.simple.domain.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MainPostDTO {
    
    private Long idx;
    private String title;
    private String userId;

    public MainPostDTO(long idx, String title, String userId) {
        this.idx = idx;
        this.title = title;
        this.userId = userId;
    }

}
