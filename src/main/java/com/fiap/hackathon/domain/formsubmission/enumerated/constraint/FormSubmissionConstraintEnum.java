package com.fiap.hackathon.domain.formsubmission.enumerated.constraint;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Getter
@RequiredArgsConstructor
@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum FormSubmissionConstraintEnum {

    @FieldNameConstants.Include T_FORM_SUBMISSION__HASH_ID_UK("O hash id informado para a submissão do formulário já encontra-se cadastrado.");

    private final String errorMessage;
}