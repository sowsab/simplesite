package com.example.simple.domain.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReqGetPostUpdateDTO {
    
    private Long idx;
    private String title;
    private String content;
    private Long userIdx;

}
