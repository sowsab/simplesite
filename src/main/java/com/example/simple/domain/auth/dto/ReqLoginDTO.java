package com.example.simple.domain.auth.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReqLoginDTO {
    
    @Valid
    @NotNull(message = "유저 정보를 입력해주세요")
    private User user;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class User {

        @NotNull(message = "아이디를 입력해주세요")
        private String id;

        @NotNull(message = "비밀번호를 입력해주세요")
        private String password;

    }

}
