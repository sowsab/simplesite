package com.example.simple.domain.auth.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReqUpdateDTO {
    
    @Valid
    @NotNull(message = "유저 정보를 입력해주세요")
    private User user;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class User {
    
        @Email(message = "올바른 이메일 형식을 입력해주세요")
        private String email;

        @NotBlank(message = "아이디를 입력해주세요")
        @Size(min = 4, message = "아이디는 4자 이상 입력해주세요")
        private String id;

        @NotBlank(message = "비밀번호를 입력해주세요")
        private String password;

        @NotBlank(message = "확인용 비밀번호를 입력해주세요")
        private String checkpw;
        
    }

}
