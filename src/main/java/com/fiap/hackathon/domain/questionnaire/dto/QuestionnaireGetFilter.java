package com.fiap.hackathon.domain.questionnaire.dto;

import com.fiap.hackathon.global.base.BasePaginationFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuestionnaireGetFilter extends BasePaginationFilter {

    @Schema(description = "Nome do questionário", example = "")
    private String name;

    @Schema(description = "Descrição do questionário", example = "")
    private String description;

    @Schema(description = "CNS profissional do questionário", example = "")
    private String professionalCns;

    @Schema(description = "CBO do questionário", example = "")
    private String cbo;

    @Schema(description = "CNES do questionário", example = "")
    private String cnes;

    @Schema(description = "INE do questionário", example = "")
    private String ine;

    public QuestionnaireGetFilter(Integer pageNumber, Integer pageSize) {
        super(pageNumber, pageSize);
    }
}