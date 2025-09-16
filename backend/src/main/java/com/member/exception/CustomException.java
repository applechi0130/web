package com.member.exception;

// 自定義異常類（建構子、欄位、方法）
// 繼承 RuntimeException，表示此異常為非檢查型異常
public class CustomException extends RuntimeException {

    private int errorCode;

    // 單純訊息
    public CustomException(String message) {
        super(message);
    }

    // 訊息加錯誤代碼
    public CustomException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
