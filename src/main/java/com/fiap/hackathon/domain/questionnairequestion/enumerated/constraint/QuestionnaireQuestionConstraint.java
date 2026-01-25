package com.fiap.hackathon.domain.questionnairequestion.enumerated.constraint;

import com.fiap.hackathon.global.constraint.IConstraint;

public class QuestionnaireQuestionConstraint implements IConstraint {

    @Override
    public String getErrorMessage(String constraintName) {
        return QuestionnaireQuestionConstraintEnum.valueOf(constraintName.toUpperCase()).getErrorMessage();
    }
}