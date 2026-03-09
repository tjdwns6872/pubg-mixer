package com.pubg.mixer.backend.common;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonResponse<T> {
    private final boolean success;
    private final String message;
    private final T data;
    private final ErrorResponse error;

    // 성공 응답 (데이터가 있는 경우)
    public static <T> CommonResponse<T> success(T data) {
        return CommonResponse.<T>builder()
                .success(true)
                .message("요청이 성공적으로 처리되었습니다.")
                .data(data)
                .build();
    }

    // 성공 응답 (데이터가 없는 경우)
    public static <T> CommonResponse<T> success() {
        return CommonResponse.<T>builder()
                .success(true)
                .message("요청이 성공적으로 처리되었습니다.")
                .build();
    }

    // 에러 응답
    public static <T> CommonResponse<T> fail(String errorCode, String message) {
        return CommonResponse.<T>builder()
                .success(false)
                .message(message)
                .error(new ErrorResponse(errorCode, message))
                .build();
    }

    @Getter
    @AllArgsConstructor
    public static class ErrorResponse {
        private final String code;
        private final String message;
    }
}