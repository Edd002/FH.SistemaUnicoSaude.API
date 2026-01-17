package com.fiap.hackathon.domain.city.enumerated.constraint;

import com.fiap.hackathon.global.constraint.IConstraint;

public final class CityConstraint implements IConstraint {

    @Override
    public String getErrorMessage(String constraintName) {
        return CityConstraintEnum.valueOf(constraintName.toUpperCase()).getErrorMessage();
    }
}