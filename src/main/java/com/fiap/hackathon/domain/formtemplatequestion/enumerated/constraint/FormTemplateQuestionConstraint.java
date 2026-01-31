package com.fiap.hackathon.domain.formtemplatequestion.enumerated.constraint;

import com.fiap.hackathon.global.constraint.IConstraint;

public class FormTemplateQuestionConstraint implements IConstraint {

    @Override
    public String getErrorMessage(String constraintName) {
        return FormTemplateQuestionConstraintEnum.valueOf(constraintName.toUpperCase()).getErrorMessage();
    }
}