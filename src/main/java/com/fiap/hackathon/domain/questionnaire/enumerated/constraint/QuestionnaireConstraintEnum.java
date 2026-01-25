package com.fiap.hackathon.domain.questionnaire.enumerated.constraint;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Getter
@RequiredArgsConstructor
@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum QuestionnaireConstraintEnum {

    @FieldNameConstants.Include T_QUESTIONNAIRE__HASH_ID_UK("O hash id informado para o questionário já encontra-se cadastrado."),
    @FieldNameConstants.Include T_QUESTIONNAIRE__NAME_UK("O nome informado para o questionário já encontra-se cadastrado.");

    private final String errorMessage;
}