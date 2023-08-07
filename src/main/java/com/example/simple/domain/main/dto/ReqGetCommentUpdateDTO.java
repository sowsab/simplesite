package com.example.simple.domain.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReqGetCommentUpdateDTO {
    
    private Long idx;
    private String content;
    private Long postIdx;

}
