package com.fiap.hackathon.domain.formtemplate.usecase;

import com.fiap.hackathon.domain.formtemplate.dto.FormTemplatePostRequestDTO;
import com.fiap.hackathon.domain.formtemplate.entity.FormTemplate;
import com.fiap.hackathon.domain.formtemplatequestion.entity.FormTemplateQuestion;
import com.fiap.hackathon.domain.question.entity.Question;
import lombok.NonNull;

import java.util.List;

public final class FormTemplateCreateUseCase {

    private final FormTemplate formTemplate;

    public FormTemplateCreateUseCase(@NonNull FormTemplatePostRequestDTO formTemplatePostRequestDTO, List<Question> questions) {
        this.formTemplate = buildFormTemplate(formTemplatePostRequestDTO, questions);
    }

    private FormTemplate buildFormTemplate(FormTemplatePostRequestDTO formTemplatePostRequestDTO, List<Question> questions) {
        return new FormTemplate(
                formTemplatePostRequestDTO.getName(),
                formTemplatePostRequestDTO.getDescription(),
                formTemplatePostRequestDTO.getProfessionalCns(),
                formTemplatePostRequestDTO.getCbo(),
                formTemplatePostRequestDTO.getCnes(),
                formTemplatePostRequestDTO.getIne(),
                Boolean.TRUE,
                questions.stream().map(FormTemplateQuestion::new).toList()
        );
    }

    public FormTemplate getBuiltedFormTemplate() {
        return this.formTemplate;
    }
}