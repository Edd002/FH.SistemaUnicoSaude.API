package com.fiap.hackathon.domain.formtemplate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.hackathon.domain.question.dto.QuestionResponseDTO;
import com.fiap.hackathon.global.base.dto.BaseResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormTemplateResponseDTO extends BaseResponseDTO {

    @Schema(description = "Hash id do template do formulário.", example = "2c2fa8b0e8b74d5c9a665b577759445a")
    @JsonProperty("hashId")
    private String hashId;

    @Schema(description = "Nome do template do formulário.", example = "FICHA DE VISITA DOMICILIAR E TERRITORIAL")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Nome do template do formulário.", example = "Formulário de ficha de visita domiciliar e territorial")
    @JsonProperty("description")
    private String description;

    @Schema(description = "CNS (Cartão Nacional de Saúde) do profissional do template do formulário.", example = "000.0000.0000.0000")
    @JsonProperty("professionalCns")
    private String professionalCns;

    @Schema(description = "CBO (Classificação Brasileira de Ocupações) do profissional do template do formulário.", example = "5151-05 – Agente Comunitário de Saúde (ACS)")
    @JsonProperty("cbo")
    private String cbo;

    @Schema(description = "CNES (Cadastro Nacional de Estabelecimentos de Saúde) do template do formulário.", example = "2005456")
    @JsonProperty("cnes")
    private String cnes;

    @Schema(description = "INE (Identificador Nacional de Equipe) do template do formulário.", example = "000000000")
    @JsonProperty("ine")
    private String ine;

    @Schema(description = "Lista de questões do template do formulário.")
    @JsonProperty("questions")
    private List<QuestionResponseDTO> questions;
}