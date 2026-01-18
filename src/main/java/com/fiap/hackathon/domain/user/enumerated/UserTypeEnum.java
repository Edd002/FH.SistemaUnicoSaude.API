package com.fiap.hackathon.domain.user.enumerated;

import com.fiap.hackathon.domain.user.entity.User;
import com.fiap.hackathon.global.util.ValidationUtil;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum UserTypeEnum {

    ADMIN("Tipo de usuário administrador."),
    PATIENT("Tipo de usuário de paciente."),
    HEALTH_PROFESSIONAL("Tipo de usuário de profissional de saúde.");

    private final String description;

    UserTypeEnum(String description) {
        this.description = description;
    }

    public static boolean isTypeAdmin(String type) {
        return ValidationUtil.isNotNull(type) && StringUtils.upperCase(type).equals(UserTypeEnum.ADMIN.name());
    }

    public static boolean isUserAdmin(User user) {
        return user.getUserType().name().equals(UserTypeEnum.ADMIN.name());
    }
}