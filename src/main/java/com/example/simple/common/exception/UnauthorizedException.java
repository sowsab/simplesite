package com.example.simple.common.exception;

public class UnauthorizedException extends RuntimeException {
    
    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException() {
        super("로그인을 해야합니다");
    }

}
