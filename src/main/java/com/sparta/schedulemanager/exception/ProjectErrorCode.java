package com.sparta.schedulemanager.exception;

// ErrorCode
public enum ProjectErrorCode implements ErrorCode {

    ERROE_WRONG_PASSWORD(401, "WRONG PASSWORD"),
    ERROR_NOT_EXIST(404, "NOT_EXIST");

    private int errorCode;
    private String reason;

    ProjectErrorCode(int errorCode, String reason) {
        this.errorCode = errorCode;
        this.reason = reason;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getReason() {
        return reason;
    }
}
