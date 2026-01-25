package com.fiap.hackathon.domain.questionnaireuser.enumerated.constraint;

import com.fiap.hackathon.global.constraint.IConstraint;

public class QuestionnaireUserConstraint implements IConstraint {

    @Override
    public String getErrorMessage(String constraintName) {
        return QuestionnaireUserConstraintEnum.valueOf(constraintName.toUpperCase()).getErrorMessage();
    }
}