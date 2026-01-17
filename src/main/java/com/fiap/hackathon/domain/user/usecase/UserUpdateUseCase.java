package com.fiap.hackathon.domain.user.usecase;

import com.fiap.hackathon.domain.city.entity.City;
import com.fiap.hackathon.domain.user.dto.UserPutRequestDTO;
import com.fiap.hackathon.domain.user.entity.User;
import com.fiap.hackathon.domain.user.enumerated.UserTypeEnum;
import lombok.NonNull;

public final class UserUpdateUseCase {

    private final User user;

    public UserUpdateUseCase(@NonNull User loggedUser, @NonNull UserTypeEnum userType, @NonNull City city, @NonNull UserPutRequestDTO userPutRequestDTO) {
        this.user = rebuildUser(loggedUser, userType, city, userPutRequestDTO);
    }

    private User rebuildUser(User loggedUser, UserTypeEnum userType, City city, UserPutRequestDTO userPutRequestDTO) {
        return loggedUser.rebuild(
                userPutRequestDTO.getName(),
                userPutRequestDTO.getEmail(),
                userPutRequestDTO.getLogin(),
                userType,
                loggedUser.getAddress().rebuild(
                        userPutRequestDTO.getAddress().getDescription(),
                        userPutRequestDTO.getAddress().getNumber(),
                        userPutRequestDTO.getAddress().getComplement(),
                        userPutRequestDTO.getAddress().getNeighborhood(),
                        userPutRequestDTO.getAddress().getCep(),
                        userPutRequestDTO.getAddress().getPostalCode(),
                        city
                )
        );
    }

    public User getRebuiltedUser() {
        return this.user;
    }
}