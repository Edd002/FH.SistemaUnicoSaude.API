package com.fiap.hackathon.domain.formtemplate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fiap.hackathon.domain.user.enumerated.UserTypeEnum;
import com.fiap.hackathon.global.base.dto.BaseRequestDTO;
import com.fiap.hackathon.global.util.EmailValidatorUtil;
import com.fiap.hackathon.global.util.deserializer.StrictStringNormalizeSpaceDeserializer;
import com.fiap.hackathon.global.util.enumerated.validation.ValueOfEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public abstract class FormTemplateRequestDTO extends BaseRequestDTO {

    //private String name;
    private String description;
    private String professionalCns;
    private String cbo;
    private String cnes;
    private String ine;
    private Boolean isActive = Boolean.TRUE;

    @Schema(description = "Nome do usuário.", example = "Roberto Afonso", maxLength = 255)
    @Size(max = 255, message = "O número de caracteres máximo para o nome do usuário é 255 caracteres.")
    @NotBlank(message = "O nome do usuário não pode ser nulo ou em branco.")
    @JsonDeserialize(using = StrictStringNormalizeSpaceDeserializer.class)
    @JsonProperty("name")
    private String name;

    @Schema(description = "E-mail do usuário.", example = "robertoafonso@email.com", maxLength = 255)
    @Size(max = 255, message = "O número de caracteres máximo para o email do usuário é 255 caracteres.")
    @Email(regexp = EmailValidatorUtil.EMAIL_REGEXP, message = "E-mail inválido.")
    @NotBlank(message = "O e-mail do usuário não pode ser nulo ou em branco.")
    @JsonDeserialize(using = StrictStringNormalizeSpaceDeserializer.class)
    @JsonProperty("email")
    private String email;

    @Schema(description = "Tipo de usuário.", example = "PATIENT", maxLength = 255)
    @Size(max = 255, message = "O número de caracteres máximo para o tipo de usuário é 255 caracteres.")
    @ValueOfEnum(enumClass = UserTypeEnum.class, message = "Tipo do usuário inválido.")
    @NotBlank(message = "O tipo de usuário não pode ser nulo ou em branco.")
    @JsonProperty("type")
    private String type;
}