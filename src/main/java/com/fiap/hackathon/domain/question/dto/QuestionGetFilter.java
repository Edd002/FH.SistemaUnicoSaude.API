package com.fiap.hackathon.domain.question.dto;

import com.fiap.hackathon.domain.question.enumerated.QuestionTopicEnum;
import com.fiap.hackathon.domain.question.enumerated.QuestionTypeEnum;
import com.fiap.hackathon.global.base.BasePaginationFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuestionGetFilter extends BasePaginationFilter {

    @Schema(description = "Título da questão.", example = "CNS ou CPF do Cidadão")
    private String title;

    @Schema(description = "Descrição da questão.", example = "(para visita periódica ou visita domiciliar para controle vetorial, usar o CNS do responsável familiar)")
    private String description;

    @Schema(description = "Tema da questão.", example = "GERAL")
    private QuestionTopicEnum topic;

    @Schema(description = "Tipo da questão.", example = "OPEN_FIELD")
    private QuestionTypeEnum type;

    public QuestionGetFilter(Integer pageNumber, Integer pageSize) {
        super(pageNumber, pageSize);
    }
}