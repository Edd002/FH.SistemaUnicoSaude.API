package com.fiap.hackathon.domain.formtemplate.enumerated.constraint;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Getter
@RequiredArgsConstructor
@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum FormTemplateConstraintEnum {

    @FieldNameConstants.Include T_FORM_TEMPLATE__HASH_ID_UK("O hash id informado para o template de formulário já encontra-se cadastrado."),
    @FieldNameConstants.Include T_FORM_TEMPLATE__NAME_UK("O nome informado para o template de formulário já encontra-se cadastrado.");

    private final String errorMessage;
}