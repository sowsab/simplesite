package com.example.simple.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReqLoginDTO {
    
    private User user;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class User {
        private String id;
        private String password;

    }

}
