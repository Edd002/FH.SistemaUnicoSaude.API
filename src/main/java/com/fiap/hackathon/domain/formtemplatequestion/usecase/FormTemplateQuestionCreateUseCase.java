package com.fiap.hackathon.domain.formtemplatequestion.usecase;

import com.fiap.hackathon.domain.formtemplate.entity.FormTemplate;
import com.fiap.hackathon.domain.formtemplatequestion.entity.FormTemplateQuestion;
import com.fiap.hackathon.domain.question.entity.Question;
import lombok.NonNull;

public class FormTemplateQuestionCreateUseCase {

    private final FormTemplateQuestion formTemplateQuestion;

    public FormTemplateQuestionCreateUseCase(@NonNull FormTemplate  formTemplate, @NonNull Question question) {
        this.formTemplateQuestion = buildFormTemplate(formTemplate, question);
    }

    private FormTemplateQuestion buildFormTemplate(FormTemplate formTemplate, Question question) {
        return new FormTemplateQuestion(formTemplate, question);
    }

    public FormTemplateQuestion getBuiltedFormTemplateQuestion() {
        return this.formTemplateQuestion;
    }
}