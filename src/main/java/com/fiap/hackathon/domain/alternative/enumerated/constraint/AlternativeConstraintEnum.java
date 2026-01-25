package com.fiap.hackathon.domain.alternative.enumerated.constraint;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Getter
@RequiredArgsConstructor
@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum AlternativeConstraintEnum {

    @FieldNameConstants.Include T_ALTERNATIVE__HASH_ID_UK("O hash id informado para a alternativa já encontra-se cadastrado."),
    @FieldNameConstants.Include T_ALTERNATIVE__OPTION_CHECK("A alternativa informada não é uma opção válida.");

    private final String errorMessage;
}