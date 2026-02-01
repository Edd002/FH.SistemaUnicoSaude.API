package com.fiap.hackathon.domain.formsubmission.usecase;

import com.fiap.hackathon.domain.formsubmission.dto.FormSubmissionPostRequestDTO;
import com.fiap.hackathon.domain.formsubmission.entity.FormSubmission;
import com.fiap.hackathon.domain.formtemplate.entity.FormTemplate;
import com.fiap.hackathon.domain.user.entity.User;
import lombok.NonNull;

public final class FormSubmissionCreateUseCase {

    private final FormSubmission formSubmission;

    public FormSubmissionCreateUseCase(@NonNull User healthProfessionalUser, @NonNull FormTemplate formTemplate, @NonNull FormSubmissionPostRequestDTO formSubmissionPostRequestDTO) {
        this.formSubmission = buildFormSubmission(healthProfessionalUser, formTemplate, formSubmissionPostRequestDTO);
    }

    private FormSubmission buildFormSubmission(User healthProfessionalUser, FormTemplate formTemplate, FormSubmissionPostRequestDTO formSubmissionPostRequestDTO) {
        return new FormSubmission(
                formSubmissionPostRequestDTO.getGeneralObservation(),
                formTemplate,
                healthProfessionalUser
        );
    }

    public FormSubmission getBuiltedFormSubmission() {
        return this.formSubmission;
    }
}