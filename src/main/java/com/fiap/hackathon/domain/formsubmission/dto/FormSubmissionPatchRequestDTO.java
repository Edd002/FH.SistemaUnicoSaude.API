package com.fiap.hackathon.domain.formsubmission.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fiap.hackathon.global.base.dto.BaseRequestDTO;
import com.fiap.hackathon.global.util.deserializer.StrictStringNormalizeSpaceDeserializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class FormSubmissionPatchRequestDTO extends BaseRequestDTO {

    @Schema(description = "Observação geral da submissão do formulário.", example = "Paciente relatou tontura constante, mas não aferiu pressão. Necessário revisão médica.")
    @JsonDeserialize(using = StrictStringNormalizeSpaceDeserializer.class)
    @JsonProperty("generalObservation")
    private String generalObservation;
}