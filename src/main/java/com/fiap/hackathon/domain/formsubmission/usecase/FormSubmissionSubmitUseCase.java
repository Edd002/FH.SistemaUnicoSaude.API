package com.fiap.hackathon.domain.formsubmission.usecase;

import com.fiap.hackathon.domain.formsubmission.entity.FormSubmission;
import lombok.NonNull;

public class FormSubmissionSubmitUseCase {

    private final FormSubmission formSubmission;

    public FormSubmissionSubmitUseCase(@NonNull FormSubmission existingFormSubmission) {
        this.formSubmission = rebuildFormSubmission(existingFormSubmission);
    }

    private FormSubmission rebuildFormSubmission(FormSubmission existingFormSubmission) {
        return existingFormSubmission.rebuild();
    }

    public FormSubmission getRebuiltedFormSubmission() {
        return this.formSubmission;
    }
}