package com.example.simple.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ResGetUpdateDTO {

    private ReqGetUpdateDataDTO reqGetUpdateDataDTO;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class ReqGetUpdateDataDTO {
    
        private String id;
        private String email;
        
    }
    
}
