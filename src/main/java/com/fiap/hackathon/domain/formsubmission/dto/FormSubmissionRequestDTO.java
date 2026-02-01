package com.fiap.hackathon.domain.formsubmission.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fiap.hackathon.global.base.dto.BaseRequestDTO;
import com.fiap.hackathon.global.util.deserializer.StrictStringNormalizeSpaceDeserializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public abstract class FormSubmissionRequestDTO extends BaseRequestDTO {

    @Schema(description = "Observação geral da submissão do formulário.", example = "Formulário poder ser submetido incompleto.")
    @JsonDeserialize(using = StrictStringNormalizeSpaceDeserializer.class)
    @JsonProperty("generalObservation")
    private String generalObservation;

    @Schema(description = "Hash id do template do formulário.", example = "2c2fa8b0e8b74d5c9a665b577759445a", maxLength = 255)
    @Size(max = 255, message = "O número de caracteres máximo para o hash id do template do formulário é 255 caracteres.")
    @NotBlank(message = "O hash id do template do formulário não pode ser nulo ou em branco.")
    @JsonDeserialize(using = StrictStringNormalizeSpaceDeserializer.class)
    @JsonProperty("hashIdFormTemplate")
    private String hashIdFormTemplate;
}