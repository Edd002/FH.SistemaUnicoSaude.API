package com.fiap.hackathon.domain.formsubmission.enumerated.constraint;

import com.fiap.hackathon.global.constraint.IConstraint;

public class FormSubmissionConstraint implements IConstraint {

    @Override
    public String getErrorMessage(String constraintName) {
        return FormSubmissionConstraintEnum.valueOf(constraintName.toUpperCase()).getErrorMessage();
    }
}