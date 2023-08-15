package com.example.simple.domain.main.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ResPostUserDTO {
    
    List<ReqPostUserDTO> reqPostUserDTOList;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class ReqPostUserDTO {
        private Long idx;
        private String title;
        private LocalDateTime createDate;
        private String userId;
    }
}
