package com.fiap.hackathon.domain.answer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.hackathon.domain.formsubmission.dto.FormSubmissionResponseDTO;
import com.fiap.hackathon.domain.question.dto.QuestionResponseDTO;
import com.fiap.hackathon.domain.user.dto.UserResponseDTO;
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

    @Schema(description = "Alternativa de visita.", example = "V_1")
    @JsonProperty("visitationAlternative")
    private String visitationAlternative;

    @Schema(description = "Resposta.", example = "80kg.")
    @JsonProperty("deliveredAnswer")
    private String deliveredAnswer;

    @Schema(description = "Questão da resposta.")
    @JsonProperty("question")
    private QuestionResponseDTO question;

    @Schema(description = "Paciente da resposta.")
    @JsonProperty("patient")
    private UserResponseDTO patient;

    @Schema(description = "Submissão de formulário da resposta.")
    @JsonProperty("formSubmission")
    private FormSubmissionResponseDTO formSubmission;
}