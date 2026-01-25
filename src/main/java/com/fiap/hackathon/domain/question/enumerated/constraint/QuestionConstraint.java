package com.fiap.hackathon.domain.question.enumerated.constraint;

import com.fiap.hackathon.global.constraint.IConstraint;

public class QuestionConstraint implements IConstraint {

    @Override
    public String getErrorMessage(String constraintName) {
        return QuestionConstraintEnum.valueOf(constraintName.toUpperCase()).getErrorMessage();
    }
}