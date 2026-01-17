package com.fiap.hackathon.domain.jwt.enumerated.constraint;

import com.fiap.hackathon.global.constraint.IConstraint;

public final class JwtConstraint implements IConstraint {

    @Override
    public String getErrorMessage(String constraintName) {
        return JwtConstraintEnum.valueOf(constraintName.toUpperCase()).getErrorMessage();
    }
}