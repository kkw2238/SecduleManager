package com.sparta.schedulemanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // 예외 핸들링을 할 수 있게 해주는 어노테이션
public class CustomExceptionHandler {

    // CustomException의 경우 해당 메서드에서 처리
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseError> handleCustomException(CustomException e) {
        ResponseError responseError = new ResponseError(e.getErrorCode());
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }
}
