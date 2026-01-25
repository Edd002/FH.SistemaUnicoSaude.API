package com.fiap.hackathon.domain.questionnairequestion.enumerated.constraint;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Getter
@RequiredArgsConstructor
@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum QuestionnaireQuestionConstraintEnum {

    @FieldNameConstants.Include T_QUESTIONNAIRE_QUESTION__HASH_ID_UK("O hash id informado para a questão do questionário já encontra-se cadastrado."),
    @FieldNameConstants.Include T_QUESTIONNAIRE_QUESTION__FK_QUESTIONNAIRE_AND_FK_QUESTION_UK("A questão informada já enconstra-se cadastrada para o questionário.");

    private final String errorMessage;
}