package com.example.jobala._core.handler;

import com.example.jobala._core.errors.apiException.*;
import com.example.jobala._core.utill.ApiUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // 비정상적일때 처리하는 응답 에러 컨트롤러 -> view(파일)를 리턴 -> @ResponseBody붙이면 됨
//RuntimeException이 터지면 해당 파일로 오류가 모인다.
//@RestControllerAdvice
public class MyApiExceptionHandler {

    @ExceptionHandler(ApiException400.class)
    public ResponseEntity<?> ex400(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(400, e.getMessage());// http body -> 구성한 객체
        return new ResponseEntity<>(apiUtil, HttpStatus.BAD_REQUEST); // http body, header
    }

    @ExceptionHandler(ApiException401.class)
    public ResponseEntity<?> ex401(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(401, e.getMessage());// http body -> 구성한 객체
        return new ResponseEntity<>(apiUtil, HttpStatus.UNAUTHORIZED); // http body, header
    }

    @ExceptionHandler(ApiException403.class)
    public ResponseEntity<?> ex403(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(403, e.getMessage());// http body -> 구성한 객체
        return new ResponseEntity<>(apiUtil, HttpStatus.FORBIDDEN); // http body, header
    }

    @ExceptionHandler(ApiException404.class)
    public ResponseEntity<?> ex404(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(404, e.getMessage());// http body -> 구성한 객체
        return new ResponseEntity<>(apiUtil, HttpStatus.NOT_FOUND); // http body, header
    }

    @ExceptionHandler(ApiException500.class)
    public ResponseEntity<?> ex500(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(500, e.getMessage());// http body -> 구성한 객체
        return new ResponseEntity<>(apiUtil, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}