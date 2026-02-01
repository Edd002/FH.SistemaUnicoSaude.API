package com.fiap.hackathon.domain.formsubmission.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.hackathon.domain.formtemplate.dto.FormTemplateResponseDTO;
import com.fiap.hackathon.global.base.dto.BaseResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormSubmissionResponseDTO extends BaseResponseDTO {

    @Schema(description = "Hash id da submissão de formulário.", example = "3d9e2b1d5f1f4f4fa671f06d34901cc3")
    @JsonProperty("hashId")
    private String hashId;

    @Schema(description = "Se o formulário está submetido (respondido).", example = "false")
    @JsonProperty("isSubmitted")
    private Boolean isSubmitted;

    @Schema(description = "Data da submissão do formulário.", type = "string", format = "date", example = "30/01/2026")
    @JsonProperty("submittedAt")
    private Date submittedAt;

    @Schema(description = "Observação geral da submissão do formulário.", example = "Formulário poder ser submetido incompleto.")
    @JsonProperty("generalObservation")
    private String generalObservation;

    @Schema(description = "Template da submissão do formulário.")
    @JsonProperty("formTemplate")
    private FormTemplateResponseDTO formTemplate;
}