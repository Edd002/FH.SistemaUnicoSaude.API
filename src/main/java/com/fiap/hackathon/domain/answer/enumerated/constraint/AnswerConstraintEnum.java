package com.fiap.hackathon.domain.answer.enumerated.constraint;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Getter
@RequiredArgsConstructor
@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum AnswerConstraintEnum {

    @FieldNameConstants.Include T_ANSWER__HASH_ID_UK("O hash id informado para a resposta já encontra-se cadastrado."),
    @FieldNameConstants.Include T_ANSWER__FK_QUESTION_AND_FK_FORM_SUBMISSION_UK("A questão informada para a resposta já enconstra-se cadastrada para a submissão do formulário."),
    @FieldNameConstants.Include T_ANSWER__VISITATION_OPTION_CHECK("A alternativa da visita não é uma opção válida.");

    private final String errorMessage;
}