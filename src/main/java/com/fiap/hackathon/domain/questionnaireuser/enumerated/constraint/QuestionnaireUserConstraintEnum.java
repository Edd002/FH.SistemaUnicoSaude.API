package com.fiap.hackathon.domain.questionnaireuser.enumerated.constraint;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Getter
@RequiredArgsConstructor
@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum QuestionnaireUserConstraintEnum {

    @FieldNameConstants.Include T_QUESTIONNAIRE_USER__HASH_ID_UK("O hash id informado para o questionário do usuário já encontra-se cadastrado."),
    @FieldNameConstants.Include T_QUESTIONNAIRE_USER__FK_QUESTIONNAIRE_AND_FK_USER_UK("O usuário informado já enconstra-se cadastrada para o questionário.");

    private final String errorMessage;
}