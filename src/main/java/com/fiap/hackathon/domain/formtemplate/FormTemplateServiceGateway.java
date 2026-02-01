package com.fiap.hackathon.domain.formtemplate;

import com.fiap.hackathon.domain.formtemplate.dto.FormTemplateGetFilter;
import com.fiap.hackathon.domain.formtemplate.dto.FormTemplatePostRequestDTO;
import com.fiap.hackathon.domain.formtemplate.dto.FormTemplatePutRequestDTO;
import com.fiap.hackathon.domain.formtemplate.dto.FormTemplateResponseDTO;
import com.fiap.hackathon.domain.formtemplate.entity.FormTemplate;
import com.fiap.hackathon.domain.formtemplate.specification.FormTemplateSpecificationBuilder;
import com.fiap.hackathon.domain.formtemplate.usecase.FormTemplateCheckForDeleteUseCase;
import com.fiap.hackathon.domain.formtemplate.usecase.FormTemplateCreateUseCase;
import com.fiap.hackathon.domain.formtemplate.usecase.FormTemplateUpdateUseCase;
import com.fiap.hackathon.domain.question.QuestionServiceGateway;
import com.fiap.hackathon.global.base.BaseServiceGateway;
import com.fiap.hackathon.global.search.builder.PageableBuilder;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class FormTemplateServiceGateway extends BaseServiceGateway<IFormTemplateRepository, FormTemplate> {

    private final PageableBuilder pageableBuilder;
    private final ModelMapper modelMapperPresenter;
    private final QuestionServiceGateway questionServiceGateway;

    @Autowired
    public FormTemplateServiceGateway(IFormTemplateRepository formTemplateRepository, PageableBuilder pageableBuilder, ModelMapper modelMapperPresenter, QuestionServiceGateway questionServiceGateway) {
        this.pageableBuilder = pageableBuilder;
        this.modelMapperPresenter = modelMapperPresenter;
        this.questionServiceGateway = questionServiceGateway;
    }

    @Transactional
    public FormTemplateResponseDTO create(FormTemplatePostRequestDTO formTemplatePostRequestDTO) {
        FormTemplate newFormTemplate = save(new FormTemplateCreateUseCase(formTemplatePostRequestDTO, questionServiceGateway.findAll()).getBuiltedFormTemplate());
        return modelMapperPresenter.map(newFormTemplate, FormTemplateResponseDTO.class);
    }

    @Transactional
    public FormTemplateResponseDTO update(String hashId, FormTemplatePutRequestDTO formTemplatePutRequestDTO) {
        FormTemplate updatedFormTemplate = new FormTemplateUpdateUseCase(findByHashId(hashId), formTemplatePutRequestDTO).getRebuiltedFormTemplate();
        return modelMapperPresenter.map(save(updatedFormTemplate), FormTemplateResponseDTO.class);
    }

    @Transactional
    public Page<FormTemplateResponseDTO> find(FormTemplateGetFilter filter) {
        Pageable pageable = pageableBuilder.build(filter);
        Optional<Specification<FormTemplate>> specification = new FormTemplateSpecificationBuilder().build(filter);
        return specification
                .map(spec -> findAll(spec, pageable))
                .orElseGet(() -> new PageImpl<>(new ArrayList<>()))
                .map(formTemplate -> modelMapperPresenter.map(formTemplate, FormTemplateResponseDTO.class));
    }

    @Transactional
    public FormTemplateResponseDTO find(String hashId) {
        return modelMapperPresenter.map(this.findByHashId(hashId), FormTemplateResponseDTO.class);
    }

    @Override
    public FormTemplate findByHashId(String hashId) {
        return super.findByHashId(hashId, String.format("O template de formulário com o hash id %s não foi encontrado.", hashId));
    }

    @Transactional
    public void delete(String hashId) {
        FormTemplate formTemplate = findByHashId(hashId);
        if (new FormTemplateCheckForDeleteUseCase(formTemplate).isAllowedToDelete()) {
            delete(formTemplate);
        }
    }
}