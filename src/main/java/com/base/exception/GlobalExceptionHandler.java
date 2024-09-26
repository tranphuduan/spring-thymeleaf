package com.base.exception;

import com.base.enums.ErrorSystem;
import com.base.model.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BaseResponse<Object>> handleBusinessException(BusinessException e) {
        return new ResponseEntity<>(new BaseResponse<>(e.getCode(), e.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new BaseResponse<>(ErrorSystem.SYSTEM_ERROR), HttpStatus.OK);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<BaseResponse<Object>> handleAuthenticationException(AuthenticationException e) {
        return new ResponseEntity<>(new BaseResponse<>(ErrorSystem.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<BaseResponse<Object>> handleAccessDeniedException(AccessDeniedException e) {
        return new ResponseEntity<>(new BaseResponse<>(ErrorSystem.FORBIDDEN), HttpStatus.FORBIDDEN);
    }
}

