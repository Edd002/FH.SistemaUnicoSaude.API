package com.fiap.hackathon.domain.formtemplate.enumerated.constraint;

import com.fiap.hackathon.global.constraint.IConstraint;

public class FormTemplateConstraint implements IConstraint {

    @Override
    public String getErrorMessage(String constraintName) {
        return FormTemplateConstraintEnum.valueOf(constraintName.toUpperCase()).getErrorMessage();
    }
}