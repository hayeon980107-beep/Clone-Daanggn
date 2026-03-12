package hy.banana.banana.common.response;

import java.time.LocalDateTime;

public record ApiResponse<T>(
        boolean success,
        T data,
        ErrorResponse error,
        String message,
        LocalDateTime timeStamp
) {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null, null, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, data, null, message, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> fail(ErrorResponse error) {
        return new ApiResponse<>(false, null, error, null,LocalDateTime.now());
    }
}
