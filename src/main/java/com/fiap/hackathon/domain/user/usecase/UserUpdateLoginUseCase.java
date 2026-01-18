package com.fiap.hackathon.domain.user.usecase;

import com.fiap.hackathon.domain.user.dto.UserUpdateLoginPatchRequestDTO;
import com.fiap.hackathon.domain.user.entity.User;
import com.fiap.hackathon.domain.user.enumerated.UserTypeEnum;
import com.fiap.hackathon.global.exception.EntityCannotBeDeletedException;
import com.fiap.hackathon.global.exception.InvalidUserPasswordException;
import lombok.NonNull;

public final class UserUpdateLoginUseCase {

    private final User user;

    public UserUpdateLoginUseCase(@NonNull User loggedUser, @NonNull UserUpdateLoginPatchRequestDTO userUpdateLoginPatchRequestDTO) {
        if (UserTypeEnum.isUserAdmin(loggedUser)) {
            throw new EntityCannotBeDeletedException("Usuários administradores não podem ter o login alterado.");
        }
        if (loggedUser.getLogin().equals(userUpdateLoginPatchRequestDTO.getNewLogin())) {
            throw new InvalidUserPasswordException("O login cadastrado não pode ser igual ao login novo.");
        }
        this.user = rebuildUser(loggedUser, userUpdateLoginPatchRequestDTO);
    }

    private User rebuildUser(User loggedUser, UserUpdateLoginPatchRequestDTO userUpdateLoginPatchRequestDTO) {
        return loggedUser.rebuild(userUpdateLoginPatchRequestDTO.getNewLogin());
    }

    public User getBuiltedUser() {
        return this.user;
    }
}