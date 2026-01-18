package com.fiap.hackathon.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fiap.hackathon.global.base.dto.BaseRequestDTO;
import com.fiap.hackathon.global.util.deserializer.StrictStringNormalizeSpaceDeserializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdateLoginPatchRequestDTO extends BaseRequestDTO {

    @Schema(description = "Novo login do usuário.", example = "robertoafonso2026", maxLength = 255)
    @Size(max = 255, message = "O número de caracteres máximo para a novo login do usuário é 255 caracteres.")
    @NotBlank(message = "O novo login do usuário não pode ser nulo ou em branco.")
    @JsonDeserialize(using = StrictStringNormalizeSpaceDeserializer.class)
    @JsonProperty("newLogin")
    private String newLogin;
}
