package com.example.simple.domain.auth.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReqUpdateDTO {
    
    @Valid
    private User user;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class User {
    
        @Email
        private String email;

        @NotBlank
        private String id;

        @NotBlank
        private String password;

        @NotBlank
        private String checkPw;
        
    }

}
