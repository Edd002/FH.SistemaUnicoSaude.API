package com.fiap.hackathon.domain.question.dto;

import com.fiap.hackathon.domain.question.enumerated.QuestionTopicEnum;
import com.fiap.hackathon.global.base.BasePaginationFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuestionGetFilter extends BasePaginationFilter {

    @Schema(description = "Título da questão.", example = "Questão sobre idade")
    private String title;

    @Schema(description = "Corpo da questão.", example = "Informe a faixa de idade")
    private String body;

    @Schema(description = "Tema da questão.", example = "HYPERTENSION")
    private QuestionTopicEnum topic;

    public QuestionGetFilter(Integer pageNumber, Integer pageSize) {
        super(pageNumber, pageSize);
    }
}