package com.fiap.hackathon.global.exception;

import com.fiap.hackathon.global.base.BaseErrorResponse;
import com.fiap.hackathon.global.base.response.error.BaseErrorResponse404;

import java.io.Serial;
import java.util.List;

public final class EntityNotFoundException extends ApiException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(message);
    }

    @Override
    public BaseErrorResponse getBaseErrorResponse() {
        return new BaseErrorResponse404(List.of(super.getMessage()));
    }
}