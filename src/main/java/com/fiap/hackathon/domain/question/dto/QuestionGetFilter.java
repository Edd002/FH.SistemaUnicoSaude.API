package com.fiap.hackathon.domain.question.dto;

import com.fiap.hackathon.domain.alternative.enumerated.AlternativeEnum;
import com.fiap.hackathon.domain.question.enumerated.QuestionTopicEnum;
import com.fiap.hackathon.global.base.BasePaginationFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuestionGetFilter extends BasePaginationFilter {

    @Schema(description = "Título da questão.", example = "Roberto Afonso")
    private String title;

    @Schema(description = "Corpo da questão.", example = "robertoafonso@email.com")
    private String body;

    @Schema(description = "Tema da questão.", example = "SANITARY_SURVEILLANCE")
    private QuestionTopicEnum topic;

    @Schema(description = "Alternativa correta da questão.", example = "A")
    private AlternativeEnum correctAlternative;

    public QuestionGetFilter(Integer pageNumber, Integer pageSize) {
        super(pageNumber, pageSize);
    }
}