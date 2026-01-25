package com.fiap.hackathon.domain.answer.enumerated.constraint;

import com.fiap.hackathon.global.constraint.IConstraint;

public class AnswerConstraint implements IConstraint {

    @Override
    public String getErrorMessage(String constraintName) {
        return AnswerConstraintEnum.valueOf(constraintName.toUpperCase()).getErrorMessage();
    }
}