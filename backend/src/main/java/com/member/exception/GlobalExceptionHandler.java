package com.member.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 攔截並處理異常，回傳適當的 HTTP 回應
@RestControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler：定義異常處理方法

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException ex) {
        // 可以根據是否有錯誤代碼回傳不同格式
        if (ex.getErrorCode() != 0) {
            Map<String, Object> response = Map.of(
                    "errorCode", ex.getErrorCode(),
                    "message", ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        } else {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // 處理其他未預期的異常
        return ResponseEntity.internalServerError().body("伺服器內部錯誤");
    }
}
