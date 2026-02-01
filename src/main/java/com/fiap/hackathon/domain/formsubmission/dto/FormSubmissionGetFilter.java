package com.fiap.hackathon.domain.formsubmission.dto;

import com.fiap.hackathon.global.base.BasePaginationFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
public class FormSubmissionGetFilter extends BasePaginationFilter {

    @Schema(description = "Se o formulário está submetido (respondido).", example = "false")
    private Boolean isSubmitted;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Schema(description = "Data da submissão do formulário.", type = "string", format = "date", example = "30/01/2026")
    private Date submittedAt;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Schema(description = "Data da sincronização da submissão do formulário.", type = "string", format = "date", example = "30/01/2026")
    private Date syncedAt;

    @Schema(description = "Observação geral da submissão do formulário.", example = "Formulário submetido incompleto.")
    private String generalObservation;

    @Schema(description = "Hash id do template do formulário.", example = "2c2fa8b0e8b74d5c9a665b577759445a")
    private String hashIdFormTemplate;

    public FormSubmissionGetFilter(Integer pageNumber, Integer pageSize) {
        super(pageNumber, pageSize);
    }
}