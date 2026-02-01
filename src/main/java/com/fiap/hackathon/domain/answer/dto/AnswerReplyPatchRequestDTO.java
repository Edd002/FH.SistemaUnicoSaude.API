package com.fiap.hackathon.domain.answer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fiap.hackathon.domain.question.enumerated.VisitationAlternativeEnum;
import com.fiap.hackathon.global.base.dto.BaseRequestDTO;
import com.fiap.hackathon.global.util.deserializer.StrictStringNormalizeSpaceDeserializer;
import com.fiap.hackathon.global.util.enumerated.validation.ValueOfEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnswerReplyPatchRequestDTO extends BaseRequestDTO {

    @Schema(description = "Alternativa de visita.", example = "V_1", maxLength = 255, allowableValues = { "V_1", "V_2", "V_3", "V_4", "V_5", "V_6", "V_7", "V_8", "V_9", "V_10", "V_11", "V_12", "V_13", "V_14", "V_15", "V_16", "V_17", "V_18", "V_19", "V_20", "V_21", "V_22", "V_23" })
    @Size(max = 255, message = "O número de caracteres máximo para a alternativa de visita de usuário é 255 caracteres.")
    @ValueOfEnum(enumClass = VisitationAlternativeEnum.class, message = "Tipo de alternativa de visita inválida.")
    @NotBlank(message = "O tipo de alternativa de visita não pode ser nula ou em branco.")
    @JsonProperty("visitationAlternative")
    private String visitationAlternative;

    @Schema(description = "Resposta.", example = "80kg.")
    @JsonDeserialize(using = StrictStringNormalizeSpaceDeserializer.class)
    @JsonProperty("deliveredAnswer")
    private String deliveredAnswer;

    @Schema(description = "Hash id da questão.", example = "9ab31457521245b3aad9185686f66c5b")
    @JsonDeserialize(using = StrictStringNormalizeSpaceDeserializer.class)
    @JsonProperty("hashIdQuestion")
    private String hashIdQuestion;

    @Schema(description = "Hash id da submissão de formulário.", example = "8af91c5643869eb28f4466a42218dc37")
    @JsonDeserialize(using = StrictStringNormalizeSpaceDeserializer.class)
    @JsonProperty("hashIdQuestion")
    private String hashIdFormSubmission;
}