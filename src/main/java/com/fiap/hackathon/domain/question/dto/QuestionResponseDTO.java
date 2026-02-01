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

    @Schema(description = "Hash id da questão.", example = "42ebf8dfbe08445ead3c524e1e691944")
    @JsonProperty("hashId")
    private String hashId;

    @Schema(description = "Título da questão.", example = "Exame")
    @JsonProperty("title")
    private String title;

    @Schema(description = "Descrição da questão.", example = "Questão de exame de hipertensão")
    @JsonProperty("description")
    private String description;

    @Schema(description = "Tema da questão.", example = "GERAL")
    @JsonProperty("topic")
    private String topic;

    @Schema(description = "Tema da questão.", example = "OPEN_FIELD")
    @JsonProperty("type")
    private String type;
}