package com.fiap.hackathon.domain.user.enumerated.constraint;

import com.fiap.hackathon.global.constraint.IConstraint;

public class UserConstraint implements IConstraint {

    @Override
    public String getErrorMessage(String constraintName) {
        return UserConstraintEnum.valueOf(constraintName.toUpperCase()).getErrorMessage();
    }
}