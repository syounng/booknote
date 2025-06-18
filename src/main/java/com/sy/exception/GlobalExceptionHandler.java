package com.sy.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        // 클라이언트에게 에러 메시지와 함께 400 상태코드 반환
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
} 