package com.sparta.schedulemanager.exception;

import lombok.Getter;

@Getter
public class ResponseError {
    private final int errorCode;
    private final String errorMessage;

    public ResponseError(ErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getReason();
    }
}
