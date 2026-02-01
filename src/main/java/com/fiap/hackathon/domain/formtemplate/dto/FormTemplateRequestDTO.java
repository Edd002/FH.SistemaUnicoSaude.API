package com.fiap.hackathon.domain.formtemplate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fiap.hackathon.global.base.dto.BaseRequestDTO;
import com.fiap.hackathon.global.util.deserializer.StrictStringNormalizeSpaceDeserializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public abstract class FormTemplateRequestDTO extends BaseRequestDTO {

    @Schema(description = "Nome do template do formulário.", example = "FICHA DE VISITA DOMICILIAR E TERRITORIAL", maxLength = 255)
    @Size(max = 255, message = "O número de caracteres máximo para o nome do formulário é 255 caracteres.")
    @NotBlank(message = "O nome do formulário não pode ser nulo ou em branco.")
    @JsonDeserialize(using = StrictStringNormalizeSpaceDeserializer.class)
    @JsonProperty("name")
    private String name;

    @Schema(description = "Nome do template do formulário.", example = "Formulário de ficha de visita domiciliar e territorial", maxLength = 255)
    @Size(max = 255, message = "O número de caracteres máximo para a descrição do formulário é 255 caracteres.")
    @NotBlank(message = "A descrição do formulário não pode ser nulo ou em branco.")
    @JsonDeserialize(using = StrictStringNormalizeSpaceDeserializer.class)
    @JsonProperty("description")
    private String description;

    @Schema(description = "CNS (Cartão Nacional de Saúde) do profissional do template do formulário.", example = "000.0000.0000.0000", maxLength = 255)
    @Size(max = 255, message = "O número de caracteres máximo para o CNS do profissional do formulário é 255 caracteres.")
    @NotBlank(message = "O CNS do profissional do formulário não pode ser nulo ou em branco.")
    @JsonDeserialize(using = StrictStringNormalizeSpaceDeserializer.class)
    @JsonProperty("professionalCns")
    private String professionalCns;

    @Schema(description = "CBO (Classificação Brasileira de Ocupações) do profissional do template do formulário.", example = "5151-05 – Agente Comunitário de Saúde (ACS)", maxLength = 255)
    @Size(max = 255, message = "O número de caracteres máximo para o CBO do profissional do formulário é 255 caracteres.")
    @NotBlank(message = "O CBO do profissional do formulário não pode ser nulo ou em branco.")
    @JsonDeserialize(using = StrictStringNormalizeSpaceDeserializer.class)
    @JsonProperty("cbo")
    private String cbo;

    @Schema(description = "CNES (Cadastro Nacional de Estabelecimentos de Saúde) do template do formulário.", example = "2005456", maxLength = 255)
    @Size(max = 255, message = "O número de caracteres máximo para o CNES do formulário é 255 caracteres.")
    @NotBlank(message = "O CNES do formulário não pode ser nulo ou em branco.")
    @JsonDeserialize(using = StrictStringNormalizeSpaceDeserializer.class)
    @JsonProperty("cnes")
    private String cnes;

    @Schema(description = "INE (Identificador Nacional de Equipe) do template do formulário.", example = "000000000", maxLength = 255)
    @Size(max = 255, message = "O número de caracteres máximo para o INE do formulário é 255 caracteres.")
    @NotBlank(message = "O INE do formulário não pode ser nulo ou em branco.")
    @JsonDeserialize(using = StrictStringNormalizeSpaceDeserializer.class)
    @JsonProperty("ine")
    private String ine;
}