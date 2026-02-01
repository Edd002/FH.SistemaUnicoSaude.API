package com.fiap.hackathon.domain.formsubmission.dto;

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
public class FormSubmissionResponseDTO extends BaseResponseDTO {

    @Schema(description = "Hash id da submissão de formulário.", example = "3d9e2b1d5f1f4f4fa671f06d34901cc3")
    @JsonProperty("hashId")
    private String hashId;
}