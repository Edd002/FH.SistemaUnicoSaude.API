package com.fiap.hackathon.domain.question.enumerated.constraint;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Getter
@RequiredArgsConstructor
@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum QuestionConstraintEnum {

    @FieldNameConstants.Include T_QUESTION__HASH_ID_UK("O hash id informado para a questão já encontra-se cadastrado."),
    @FieldNameConstants.Include T_QUESTION__TOPIC_CHECK("O tema da questão não é uma opção válida.");

    private final String errorMessage;
}