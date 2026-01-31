package com.fiap.hackathon.domain.formtemplatequestion.enumerated.constraint;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Getter
@RequiredArgsConstructor
@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum FormTemplateQuestionConstraintEnum {

    @FieldNameConstants.Include T_FORM_TEMPLATE_QUESTION__HASH_ID_UK("O hash id informado para a questão do formulário já encontra-se cadastrado."),
    @FieldNameConstants.Include T_FORM_TEMPLATE_QUESTION__FK_FORM_TEMPLATE_AND_FK_QUESTION_UK("A questão informada já enconstra-se cadastrada para o formulário.");

    private final String errorMessage;
}