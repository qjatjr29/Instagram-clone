package com.beomstagram.common;

import lombok.Getter;

@Getter
public enum ApiResponseMessage {

    SUCCESS_REQUEST(200, "API 요청이 성공했습니다."),
    INVALID_REQUEST(201, "불가능한 요청입니다."),
    SERVER_ERROR(500, "에러가 발생했습니다.");

    Integer code;
    String message;

    ApiResponseMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
