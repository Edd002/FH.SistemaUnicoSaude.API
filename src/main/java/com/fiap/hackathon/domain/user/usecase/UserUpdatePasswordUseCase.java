package com.fiap.hackathon.domain.user.usecase;

import com.fiap.hackathon.domain.user.dto.UserUpdatePasswordPatchRequestDTO;
import com.fiap.hackathon.domain.user.entity.User;
import com.fiap.hackathon.global.exception.InvalidUserPasswordException;
import com.fiap.hackathon.global.util.CryptoUtil;
import lombok.NonNull;

public final class UserUpdatePasswordUseCase {

    private final User user;

    public UserUpdatePasswordUseCase(@NonNull User loggedUser, @NonNull UserUpdatePasswordPatchRequestDTO userUpdatePasswordPatchRequestDTO, @NonNull String passwordCryptoKey) {
        CryptoUtil crypto = CryptoUtil.newInstance(passwordCryptoKey);
        if (!crypto.matches(userUpdatePasswordPatchRequestDTO.getActualPassword(), loggedUser.getPassword())) {
            throw new InvalidUserPasswordException("A senha cadastrada é diferente da senha atual.");
        }
        if (crypto.matches(userUpdatePasswordPatchRequestDTO.getNewPassword(), loggedUser.getPassword())) {
            throw new InvalidUserPasswordException("A senha cadastrada não pode ser igual a senha nova.");
        }
        if (!userUpdatePasswordPatchRequestDTO.getNewPassword().equals(userUpdatePasswordPatchRequestDTO.getNewPasswordConfirmation())) {
            throw new InvalidUserPasswordException("A senha nova é difente da confirmação de senha nova.");
        }
        this.user = loggedUser;
        this.user.setEncryptedPassword(passwordCryptoKey, userUpdatePasswordPatchRequestDTO.getNewPassword());
    }

    public User getBuiltedUser() {
        return this.user;
    }
}