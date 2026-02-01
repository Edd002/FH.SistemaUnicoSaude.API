package com.fiap.hackathon.domain.formsubmission;

import com.fiap.hackathon.domain.formsubmission.dto.SubmitFormPatchRequestDTO;
import com.fiap.hackathon.domain.formsubmission.entity.FormSubmission;
import com.fiap.hackathon.global.base.BaseServiceGateway;
import com.fiap.hackathon.global.search.builder.PageableBuilder;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormSubmissionServiceGateway extends BaseServiceGateway<IFormSubmissionRepository, FormSubmission> {

    private final PageableBuilder pageableBuilder;
    private final ModelMapper modelMapperPresenter;

    @Autowired
    public FormSubmissionServiceGateway(IFormSubmissionRepository formSubmissionRepository, PageableBuilder pageableBuilder, ModelMapper modelMapperPresenter) {
        this.pageableBuilder = pageableBuilder;
        this.modelMapperPresenter = modelMapperPresenter;
    }

    @Transactional
    public void submitForm(SubmitFormPatchRequestDTO submitFormPatchRequestDTO) {
    }
}