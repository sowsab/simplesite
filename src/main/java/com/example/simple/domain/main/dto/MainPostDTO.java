package com.example.simple.domain.main.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class MainPostDTO {
    
    private Long idx;
    private String title;
    private Long userIdx;
    private String userId;
    private LocalDateTime createDate;

}
