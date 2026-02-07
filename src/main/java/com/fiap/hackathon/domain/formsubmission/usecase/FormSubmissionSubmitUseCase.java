package com.fiap.hackathon.domain.formsubmission.usecase;

import com.fiap.hackathon.domain.formsubmission.dto.FormSubmissionPatchRequestDTO;
import com.fiap.hackathon.domain.formsubmission.entity.FormSubmission;
import lombok.NonNull;

public class FormSubmissionSubmitUseCase {

    private final FormSubmission formSubmission;

    public FormSubmissionSubmitUseCase(@NonNull FormSubmission existingFormSubmission, @NonNull FormSubmissionPatchRequestDTO formSubmissionPatchRequestDTO) {
        this.formSubmission = rebuildFormSubmission(existingFormSubmission, formSubmissionPatchRequestDTO);
    }

    private FormSubmission rebuildFormSubmission(FormSubmission existingFormSubmission, FormSubmissionPatchRequestDTO formSubmissionPatchRequestDTO) {
        return existingFormSubmission.rebuild(formSubmissionPatchRequestDTO.getGeneralObservation());
    }

    public FormSubmission getRebuiltedFormSubmission() {
        return this.formSubmission;
    }
}