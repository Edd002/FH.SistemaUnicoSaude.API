package com.fiap.hackathon.domain.formtemplate.dto;

import com.fiap.hackathon.global.base.BasePaginationFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FormTemplateGetFilter extends BasePaginationFilter {

    @Schema(description = "Nome do template do formulário.", example = "FICHA DE VISITA DOMICILIAR E TERRITORIAL")
    private String name;

    @Schema(description = "Descrição do template do formulário.", example = "Formulário de ficha de visita domiciliar e territorial")
    private String description;

    @Schema(description = "CNS (Cartão Nacional de Saúde) do profissional do template do formulário.", example = "000.0000.0000.0000")
    private String professionalCns;

    @Schema(description = "CBO (Classificação Brasileira de Ocupações) do profissional do template do formulário.", example = "5151-05 – Agente Comunitário de Saúde (ACS)")
    private String cbo;

    @Schema(description = "CNES (Cadastro Nacional de Estabelecimentos de Saúde) do template do formulário.", example = "2005456")
    private String cnes;

    @Schema(description = "INE (Identificador Nacional de Equipe) do template do formulário.", example = "000000000")
    private String ine;

    public FormTemplateGetFilter(Integer pageNumber, Integer pageSize) {
        super(pageNumber, pageSize);
    }
}