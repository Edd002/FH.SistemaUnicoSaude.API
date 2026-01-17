package com.fiap.hackathon.global.exception;

import com.fiap.hackathon.global.base.BaseErrorResponse;
import com.fiap.hackathon.global.base.response.error.BaseErrorResponse409;

import java.io.Serial;
import java.util.List;

public class EntityCannotBeDeletedException extends ApiException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EntityCannotBeDeletedException(String message) {
        super(message);
    }

    @Override
    public BaseErrorResponse getBaseErrorResponse() {
        return new BaseErrorResponse409(List.of(super.getMessage()));
    }
}