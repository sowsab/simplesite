package com.example.simple.common.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.simple.common.dto.ResponseDTO;
import com.example.simple.common.exception.BadRequestException;
import com.example.simple.common.exception.CustomNotFoundException;
import com.example.simple.common.exception.ForbbidenException;
import com.example.simple.common.exception.UnauthorizedException;

@RestControllerAdvice
public class RestExceptionHandler {

        @ExceptionHandler(BadRequestException.class)
        public ResponseEntity<?> handleBadRequestException(Exception exception) {
                exception.printStackTrace();
                return new ResponseEntity<>(
                                ResponseDTO.builder()
                                                .code(1)
                                                .message(exception.getMessage())
                                                .build(),
                                HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<?> handleException(Exception exception) {
                exception.printStackTrace();
                return new ResponseEntity<>(
                                ResponseDTO.builder()
                                                .code(1)
                                                .message(exception.getMessage())
                                                .build(),
                                HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(UnauthorizedException.class)
        public ResponseEntity<ResponseDTO<?>> handleUnauthorizedException(Exception exception) {
                exception.printStackTrace();
                return new ResponseEntity<>(
                                ResponseDTO.builder()
                                                .code(1)
                                                .message(exception.getMessage())
                                                .build(),
                                HttpStatus.UNAUTHORIZED);
        }

        @ExceptionHandler(ForbbidenException.class)
        public ResponseEntity<ResponseDTO<?>> handleForbbidenException(Exception exception) {
                exception.printStackTrace();
                return new ResponseEntity<>(
                                ResponseDTO.builder()
                                                .code(1)
                                                .message(exception.getMessage())
                                                .build(),
                                HttpStatus.FORBIDDEN);
        }

        @ExceptionHandler(CustomNotFoundException.class)
        public ResponseEntity<ResponseDTO<?>> handleCustomNotFoundException(Exception exception) {
                exception.printStackTrace();
                return new ResponseEntity<>(
                                ResponseDTO.builder()
                                                .code(1)
                                                .message(exception.getMessage())
                                                .build(),
                                HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ResponseDTO<?>> handleMethodArgumentNotValidException(
                        MethodArgumentNotValidException ex) {
                BindingResult bindingResult = ex.getBindingResult();
                FieldError fieldError = bindingResult.getFieldError();

                // 유효성 검사 실패한 필드와 메시지 추출
                String errorMessage = fieldError != null ? fieldError.getDefaultMessage() : "유효성 검사 실패";

                // 응답 생성
                return new ResponseEntity<>(
                                ResponseDTO.builder()
                                                .code(1)
                                                .message(errorMessage)
                                                .build(),
                                HttpStatus.BAD_REQUEST);
        }

}
