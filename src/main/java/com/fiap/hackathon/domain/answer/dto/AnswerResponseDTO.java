package com.fiap.hackathon.domain.answer.dto;

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
public class AnswerResponseDTO extends BaseResponseDTO {

    @Schema(description = "Hash id da resposta.", example = "1f93a66e3c63459191bb0150fa09586d")
    @JsonProperty("hashId")
    private String hashId;
}