package com.example.simple.common.exception;

public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException(String message) {
        super(message);
    }

    public CustomNotFoundException() {
        super("페이지를 찾을 수 없습니다");
    }
}
