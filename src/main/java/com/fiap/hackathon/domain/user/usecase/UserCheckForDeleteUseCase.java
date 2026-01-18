package com.fiap.hackathon.domain.user.usecase;

import com.fiap.hackathon.domain.user.entity.User;
import com.fiap.hackathon.domain.user.enumerated.UserTypeEnum;
import com.fiap.hackathon.global.exception.EntityCannotBeDeletedException;
import lombok.NonNull;

public class UserCheckForDeleteUseCase {

    private final Boolean isAllowedToDelete;

    public UserCheckForDeleteUseCase(@NonNull User loggedUser) {
        if (UserTypeEnum.isUserAdmin(loggedUser)) {
            throw new EntityCannotBeDeletedException("Usuários administradores não podem ser excluídos.");
        }
        this.isAllowedToDelete = true;
    }

    public Boolean isAllowedToDelete() {
        return this.isAllowedToDelete;
    }
}