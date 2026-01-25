package com.fiap.hackathon.domain.answer.enumerated.constraint;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Getter
@RequiredArgsConstructor
@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum AnswerConstraintEnum {

    @FieldNameConstants.Include T_ANSWER__HASH_ID_UK("O hash id informado para a resposta já encontra-se cadastrado."),
    @FieldNameConstants.Include T_ANSWER__ANSWERED_ALTERNATIVE_CHECK("A alternativa definida como resposta não é uma opção válida.");

    private final String errorMessage;
}