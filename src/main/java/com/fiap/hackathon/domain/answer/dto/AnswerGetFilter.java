package com.fiap.hackathon.domain.answer.dto;

import com.fiap.hackathon.domain.question.enumerated.VisitationAlternativeEnum;
import com.fiap.hackathon.global.base.BasePaginationFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnswerGetFilter extends BasePaginationFilter {

    @Schema(description = "Alternativa de visita.", example = "V_1")
    private VisitationAlternativeEnum visitationAlternative;

    @Schema(description = "Resposta.", example = "80kg.")
    private String deliveredAnswer;

    @Schema(description = "Hash id da questão.", example = "9ab31457521245b3aad9185686f66c5b")
    private String hashIdQuestion;

    @Schema(description = "Hash id do paciente.", example = "7ed80b4757554fa28e5588f41808cd48")
    private String hashIdPatient;

    @Schema(description = "Hash id da submissão de formulário.", example = "8af91c5643869eb28f4466a42218dc37")
    private String hashIdFormSubmission;

    public AnswerGetFilter(Integer pageNumber, Integer pageSize) {
        super(pageNumber, pageSize);
    }
}