package com.fiap.hackathon.global.exception;

import com.fiap.hackathon.global.base.BaseErrorResponse;

import java.io.Serial;

public abstract class ApiException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ApiException() {
        super();
    }

    public ApiException(String message) {
        super(message);
    }

    public abstract BaseErrorResponse getBaseErrorResponse();
}