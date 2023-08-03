package com.example.simple.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResGetUpdateDTO {

    private ReqGetUpdateDataDTO ReqGetUpdateDataDTO;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class ReqGetUpdateDataDTO {
    
        private String id;
        private String email;
        
    }
    
}
