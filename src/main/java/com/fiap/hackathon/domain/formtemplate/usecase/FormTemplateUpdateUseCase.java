package com.fiap.hackathon.domain.formtemplate.usecase;

import com.fiap.hackathon.domain.formtemplate.dto.FormTemplatePutRequestDTO;
import com.fiap.hackathon.domain.formtemplate.entity.FormTemplate;
import lombok.NonNull;

public class FormTemplateUpdateUseCase {

    private final FormTemplate formTemplate;

    public FormTemplateUpdateUseCase(@NonNull FormTemplate existingFormTemplate, @NonNull FormTemplatePutRequestDTO formTemplatePutRequestDTO) {
        this.formTemplate = rebuildFormTemplate(existingFormTemplate, formTemplatePutRequestDTO);
    }

    private FormTemplate rebuildFormTemplate(FormTemplate existingFormTemplate, FormTemplatePutRequestDTO formTemplatePutRequestDTO) {
        return existingFormTemplate.rebuild(
                formTemplatePutRequestDTO.getName(),
                formTemplatePutRequestDTO.getDescription(),
                formTemplatePutRequestDTO.getProfessionalCns(),
                formTemplatePutRequestDTO.getCbo(),
                formTemplatePutRequestDTO.getCnes(),
                formTemplatePutRequestDTO.getIne(),
                Boolean.TRUE
        );
    }

    public FormTemplate getRebuiltedFormTemplate() {
        return this.formTemplate;
    }
}