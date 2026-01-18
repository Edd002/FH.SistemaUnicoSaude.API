package com.fiap.hackathon.domain.address.enumerated.constraint;

import com.fiap.hackathon.global.constraint.IConstraint;

public final class AddressConstraint implements IConstraint {

    @Override
    public String getErrorMessage(String constraintName) {
        return AddressConstraintEnum.valueOf(constraintName.toUpperCase()).getErrorMessage();
    }
}