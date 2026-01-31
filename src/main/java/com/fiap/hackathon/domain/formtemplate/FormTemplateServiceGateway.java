package com.fiap.hackathon.domain.formtemplate;

import com.fiap.hackathon.domain.formtemplate.dto.FormTemplateGetFilter;
import com.fiap.hackathon.domain.formtemplate.dto.FormTemplateResponseDTO;
import com.fiap.hackathon.domain.formtemplate.entity.FormTemplate;
import com.fiap.hackathon.domain.formtemplate.specification.FormTemplateSpecificationBuilder;
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

    @Autowired
    public FormTemplateServiceGateway(IFormTemplateRepository formTemplateRepository, PageableBuilder pageableBuilder, ModelMapper modelMapperPresenter) {
        this.pageableBuilder = pageableBuilder;
        this.modelMapperPresenter = modelMapperPresenter;
    }

    @Transactional
    public Page<FormTemplateResponseDTO> find(FormTemplateGetFilter filter) {
        Pageable pageable = pageableBuilder.build(filter);
        Optional<Specification<FormTemplate>> specification = new FormTemplateSpecificationBuilder().build(filter);
        return specification
                .map(spec -> findAll(spec, pageable))
                .orElseGet(() -> new PageImpl<>(new ArrayList<>()))
                .map(user -> modelMapperPresenter.map(user, FormTemplateResponseDTO.class));
    }

    @Transactional
    public FormTemplateResponseDTO find(String hashId) {
        return modelMapperPresenter.map(this.findByHashId(hashId), FormTemplateResponseDTO.class);
    }

    @Override
    public FormTemplate findByHashId(String hashId) {
        return super.findByHashId(hashId, String.format("O formulário com o hash id %s não foi encontrado.", hashId));
    }
}