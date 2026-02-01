package com.fiap.hackathon.domain.formtemplate.usecase;

import com.fiap.hackathon.domain.formtemplate.entity.FormTemplate;
import com.fiap.hackathon.global.exception.EntityCannotBeDeletedException;
import lombok.NonNull;

public class FormTemplateCheckForDeleteUseCase {

    private final Boolean isAllowedToDelete;

    public FormTemplateCheckForDeleteUseCase(@NonNull FormTemplate formTemplate) {
        if (!formTemplate.getFormSubmissions().isEmpty()) {
            throw new EntityCannotBeDeletedException("Já existem submissões para o formulário selecionado e por isso não pode ser excluído.");
        }
        this.isAllowedToDelete = true;
    }

    public Boolean isAllowedToDelete() {
        return this.isAllowedToDelete;
    }
}