package com.fiap.hackathon.domain.questionnaire.enumerated.constraint;

import com.fiap.hackathon.global.constraint.IConstraint;

public class QuestionnaireConstraint implements IConstraint {

    @Override
    public String getErrorMessage(String constraintName) {
        return QuestionnaireConstraintEnum.valueOf(constraintName.toUpperCase()).getErrorMessage();
    }
}