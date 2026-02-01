package com.fiap.hackathon.domain.formtemplate.usecase;

import com.fiap.hackathon.domain.formtemplate.dto.FormTemplatePostRequestDTO;
import com.fiap.hackathon.domain.formtemplate.entity.FormTemplate;
import lombok.NonNull;

public final class FormTemplateCreateUseCase {

    private final FormTemplate formTemplate;

    public FormTemplateCreateUseCase(@NonNull FormTemplatePostRequestDTO formTemplatePostRequestDTO) {
        this.formTemplate = buildFormTemplate(formTemplatePostRequestDTO);
    }

    private FormTemplate buildFormTemplate(FormTemplatePostRequestDTO formTemplatePostRequestDTO) {
        return new FormTemplate(
                formTemplatePostRequestDTO.getName(),
                formTemplatePostRequestDTO.getDescription(),
                formTemplatePostRequestDTO.getProfessionalCns(),
                formTemplatePostRequestDTO.getCbo(),
                formTemplatePostRequestDTO.getCnes(),
                formTemplatePostRequestDTO.getIne(),
                Boolean.TRUE
        );
    }

    public FormTemplate getBuiltedFormTemplate() {
        return this.formTemplate;
    }
}