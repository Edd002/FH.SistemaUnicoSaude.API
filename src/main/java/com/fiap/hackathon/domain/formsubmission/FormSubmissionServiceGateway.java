package com.fiap.hackathon.domain.formsubmission;

import com.fiap.hackathon.domain.formsubmission.dto.FormSubmissionGetFilter;
import com.fiap.hackathon.domain.formsubmission.dto.FormSubmissionPostRequestDTO;
import com.fiap.hackathon.domain.formsubmission.dto.FormSubmissionResponseDTO;
import com.fiap.hackathon.domain.formsubmission.entity.FormSubmission;
import com.fiap.hackathon.domain.formsubmission.specification.FormSubmissionSpecificationBuilder;
import com.fiap.hackathon.domain.formsubmission.usecase.FormSubmissionCheckForDeleteUseCase;
import com.fiap.hackathon.domain.formsubmission.usecase.FormSubmissionCreateUseCase;
import com.fiap.hackathon.domain.formsubmission.usecase.FormSubmissionSubmitUseCase;
import com.fiap.hackathon.domain.formtemplate.FormTemplateServiceGateway;
import com.fiap.hackathon.domain.formtemplate.entity.FormTemplate;
import com.fiap.hackathon.domain.user.authuser.AuthUserContextHolder;
import com.fiap.hackathon.domain.user.entity.User;
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
public class FormSubmissionServiceGateway extends BaseServiceGateway<IFormSubmissionRepository, FormSubmission> {

    private final PageableBuilder pageableBuilder;
    private final ModelMapper modelMapperPresenter;
    private final FormTemplateServiceGateway formTemplateServiceGateway;

    @Autowired
    public FormSubmissionServiceGateway(IFormSubmissionRepository formSubmissionRepository, PageableBuilder pageableBuilder, ModelMapper modelMapperPresenter, FormTemplateServiceGateway formTemplateServiceGateway) {
        this.pageableBuilder = pageableBuilder;
        this.modelMapperPresenter = modelMapperPresenter;
        this.formTemplateServiceGateway = formTemplateServiceGateway;
    }

    @Transactional
    public FormSubmissionResponseDTO create(FormSubmissionPostRequestDTO formSubmissionPostRequestDTO) {
        User healthProfessionalUser = AuthUserContextHolder.getAuthUser();
        FormTemplate formTemplate = formTemplateServiceGateway.findByHashId(formSubmissionPostRequestDTO.getHashIdFormTemplate());
        FormSubmission newFormSubmission = save(new FormSubmissionCreateUseCase(healthProfessionalUser, formTemplate, formSubmissionPostRequestDTO).getBuiltedFormSubmission());
        return modelMapperPresenter.map(newFormSubmission, FormSubmissionResponseDTO.class);
    }

    @Transactional
    public FormSubmissionResponseDTO submitForm(String hashId) {
        FormSubmission existingFormSubmission = findByHashId(hashId);
        FormSubmission updatedFormSubmission = save(new FormSubmissionSubmitUseCase(existingFormSubmission).getRebuiltedFormSubmission());
        return modelMapperPresenter.map(updatedFormSubmission, FormSubmissionResponseDTO.class);
    }

    @Transactional
    public Page<FormSubmissionResponseDTO> find(FormSubmissionGetFilter filter) {
        Pageable pageable = pageableBuilder.build(filter);
        Optional<Specification<FormSubmission>> specification = new FormSubmissionSpecificationBuilder().build(filter);
        return specification
                .map(spec -> findAll(spec, pageable))
                .orElseGet(() -> new PageImpl<>(new ArrayList<>()))
                .map(formSubmission -> modelMapperPresenter.map(formSubmission, FormSubmissionResponseDTO.class));
    }

    @Transactional
    public FormSubmissionResponseDTO find(String hashId) {
        return modelMapperPresenter.map(this.findByHashId(hashId), FormSubmissionResponseDTO.class);
    }

    @Override
    public FormSubmission findByHashId(String hashId) {
        return super.findByHashId(hashId, String.format("A submissão de formulário com o hash id %s não foi encontrada.", hashId));
    }

    @Transactional
    public void delete(String hashId) {
        FormSubmission formSubmission = findByHashId(hashId);
        if (new FormSubmissionCheckForDeleteUseCase(formSubmission).isAllowedToDelete()) {
            delete(formSubmission);
        }
    }
}