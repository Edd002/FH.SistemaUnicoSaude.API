package com.fiap.hackathon.domain.question.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.hackathon.global.base.dto.BaseResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponseDTO extends BaseResponseDTO {

    @Schema(description = "Título da questão.", example = "Roberto Afonso")
    @JsonProperty("title")
    private String title;

    @Schema(description = "Descrição da questão.", example = "robertoafonso@email.com")
    @JsonProperty("description")
    private String description;
}