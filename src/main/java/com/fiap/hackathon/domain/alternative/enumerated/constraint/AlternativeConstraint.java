package com.fiap.hackathon.domain.alternative.enumerated.constraint;

import com.fiap.hackathon.global.constraint.IConstraint;

public class AlternativeConstraint implements IConstraint {

    @Override
    public String getErrorMessage(String constraintName) {
        return AlternativeConstraintEnum.valueOf(constraintName.toUpperCase()).getErrorMessage();
    }
}