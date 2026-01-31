package com.fiap.hackathon.domain.formtemplate.dto;

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
public class FormTemplateResponseDTO extends BaseResponseDTO {

    @Schema(description = "Nome do questionário", example = "")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Descrição do questionário", example = "")
    @JsonProperty("description")
    private String description;

    @Schema(description = "CNS profissional do questionário", example = "")
    @JsonProperty("professionalCns")
    private String professionalCns;

    @Schema(description = "CBO do questionário", example = "")
    @JsonProperty("cbo")
    private String cbo;

    @Schema(description = "CNES do questionário", example = "")
    @JsonProperty("cnes")
    private String cnes;

    @Schema(description = "INE do questionário", example = "")
    @JsonProperty("ine")
    private String ine;
}