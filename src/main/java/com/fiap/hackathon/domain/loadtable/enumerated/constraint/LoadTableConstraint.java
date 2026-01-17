package com.fiap.hackathon.domain.loadtable.enumerated.constraint;

import com.fiap.hackathon.global.constraint.IConstraint;

public final class LoadTableConstraint implements IConstraint {

    @Override
    public String getErrorMessage(String constraintName) {
        return LoadTableConstraintEnum.valueOf(constraintName.toUpperCase()).getErrorMessage();
    }
}