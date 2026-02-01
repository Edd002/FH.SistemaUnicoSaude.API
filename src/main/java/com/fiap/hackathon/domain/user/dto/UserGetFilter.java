package com.fiap.hackathon.domain.user.dto;

import com.fiap.hackathon.domain.user.enumerated.UserTypeEnum;
import com.fiap.hackathon.global.base.BasePaginationFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public final class UserGetFilter extends BasePaginationFilter {

    @Schema(description = "Nome do usuário.", example = "Roberto Afonso")
    private String name;

    @Schema(description = "E-mail do usuário.", example = "robertoafonso@email.com")
    private String email;

    @Schema(description = "Tipo do usuário.", example = "PATIENT")
    private UserTypeEnum type;

    public UserGetFilter(Integer pageNumber, Integer pageSize) {
        super(pageNumber, pageSize);
    }
}