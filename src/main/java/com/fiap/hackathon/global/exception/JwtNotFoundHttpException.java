package com.fiap.hackathon.global.exception;

import com.fiap.hackathon.global.base.BaseErrorResponse;
import com.fiap.hackathon.global.base.response.error.BaseErrorResponse401;

import java.io.Serial;
import java.util.List;

public class JwtNotFoundHttpException extends ApiException {

  @Serial
  private static final long serialVersionUID = 1L;

  public JwtNotFoundHttpException(String message) {
    super(message);
  }

  @Override
  public BaseErrorResponse getBaseErrorResponse() {
    return new BaseErrorResponse401(List.of(super.getMessage()));
  }
}