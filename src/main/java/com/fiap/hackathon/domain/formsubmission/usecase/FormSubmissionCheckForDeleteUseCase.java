package com.fiap.hackathon.domain.formsubmission.usecase;

import com.fiap.hackathon.domain.formsubmission.entity.FormSubmission;
import com.fiap.hackathon.global.exception.EntityCannotBeDeletedException;
import lombok.NonNull;

public class FormSubmissionCheckForDeleteUseCase {

    private final Boolean isAllowedToDelete;

    public FormSubmissionCheckForDeleteUseCase(@NonNull FormSubmission formSubmission) {
        if (formSubmission.getIsSubmitted()) {
            throw new EntityCannotBeDeletedException("A submissão do formulário já foi concluída e por isso não pode ser excluída.");
        }
        this.isAllowedToDelete = true;
    }

    public Boolean isAllowedToDelete() {
        return this.isAllowedToDelete;
    }
}