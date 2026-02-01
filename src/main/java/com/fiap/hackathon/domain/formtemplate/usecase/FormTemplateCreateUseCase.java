package com.fiap.hackathon.domain.formtemplate.usecase;

import com.fiap.hackathon.domain.formtemplate.dto.FormTemplatePostRequestDTO;
import com.fiap.hackathon.domain.formtemplate.entity.FormTemplate;
import com.fiap.hackathon.domain.formtemplatequestion.entity.FormTemplateQuestion;
import com.fiap.hackathon.domain.question.entity.Question;
import com.fiap.hackathon.domain.user.entity.User;
import lombok.NonNull;

import java.util.List;

public final class FormTemplateCreateUseCase {

    private final FormTemplate formTemplate;

    public FormTemplateCreateUseCase(@NonNull User loggedHealthProfessionalUser, @NonNull FormTemplatePostRequestDTO formTemplatePostRequestDTO, List<Question> questions) {
        this.formTemplate = buildFormTemplate(loggedHealthProfessionalUser, formTemplatePostRequestDTO, questions);
    }

    private FormTemplate buildFormTemplate(User loggedHealthProfessionalUser, FormTemplatePostRequestDTO formTemplatePostRequestDTO, List<Question> questions) {
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