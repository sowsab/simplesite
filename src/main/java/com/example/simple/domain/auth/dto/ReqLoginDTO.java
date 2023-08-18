package com.example.simple.domain.auth.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReqLoginDTO {
    
    @Valid
    private User user;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class User {

        @NotBlank(message = "아이디를 입력해주세요")
        private String id;

        @NotBlank(message = "비밀번호를 입력해주세요")
        private String password;

    }

}
