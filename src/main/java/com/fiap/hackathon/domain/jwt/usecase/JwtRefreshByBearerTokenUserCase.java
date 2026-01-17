package com.fiap.hackathon.domain.jwt.usecase;

import com.fiap.hackathon.domain.jwt.entity.Jwt;
import com.fiap.hackathon.global.exception.InvalidBearerTokenHttpException;

import java.util.Date;

public class JwtRefreshByBearerTokenUserCase {

    private final Jwt jwt;

    public JwtRefreshByBearerTokenUserCase(Jwt existingJwt, int millisecondsToExpireJwt) {
        if (!new JwtIsActiveUseCase(existingJwt, millisecondsToExpireJwt).isActive()) {
            throw new InvalidBearerTokenHttpException();
        }
        this.jwt = existingJwt;
        this.jwt.setUpdatedIn(new Date());
    }

    public Jwt getBuiltedJwt() {
        return this.jwt;
    }
}