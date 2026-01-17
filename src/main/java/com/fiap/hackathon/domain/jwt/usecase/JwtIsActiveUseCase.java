package com.fiap.hackathon.domain.jwt.usecase;

import com.fiap.hackathon.domain.jwt.entity.Jwt;
import com.fiap.hackathon.global.util.DateTimeUtil;
import com.fiap.hackathon.global.util.ValidationUtil;

public class JwtIsActiveUseCase {

    private final Boolean isActive;

    public JwtIsActiveUseCase(Jwt jwt, int millisecondsToExpireJwt) {
        this.isActive = ValidationUtil.isNotNull(jwt) && DateTimeUtil.beforeNow(DateTimeUtil.addMilliseconds(jwt.getUpdatedIn(), millisecondsToExpireJwt));
    }

    public Boolean isActive() {
        return this.isActive;
    }
}