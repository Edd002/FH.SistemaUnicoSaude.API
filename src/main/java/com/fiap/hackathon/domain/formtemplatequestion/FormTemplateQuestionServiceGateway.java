package com.fiap.hackathon.domain.formtemplatequestion;

import com.fiap.hackathon.domain.formtemplate.IFormTemplateRepository;
import com.fiap.hackathon.domain.formtemplatequestion.entity.FormTemplateQuestion;
import com.fiap.hackathon.global.base.BaseServiceGateway;
import com.fiap.hackathon.global.search.builder.PageableBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormTemplateQuestionServiceGateway extends BaseServiceGateway<IFormTemplateQuestion, FormTemplateQuestion> {

    private final PageableBuilder pageableBuilder;
    private final ModelMapper modelMapperPresenter;

    @Autowired
    public FormTemplateQuestionServiceGateway(IFormTemplateRepository formTemplateRepository, PageableBuilder pageableBuilder, ModelMapper modelMapperPresenter) {
        this.pageableBuilder = pageableBuilder;
        this.modelMapperPresenter = modelMapperPresenter;
    }
}