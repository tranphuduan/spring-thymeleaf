package com.base.exception;

import com.base.enums.ErrorSystem;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final String code;

    public BusinessException(ErrorSystem error) {
        super(error.mess());
        this.code = error.code();
    }

}