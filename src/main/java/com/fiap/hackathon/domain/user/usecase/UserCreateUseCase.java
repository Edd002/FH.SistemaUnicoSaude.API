package com.fiap.hackathon.domain.user.usecase;

import com.fiap.hackathon.domain.address.entity.Address;
import com.fiap.hackathon.domain.city.entity.City;
import com.fiap.hackathon.domain.user.dto.UserPostRequestDTO;
import com.fiap.hackathon.domain.user.entity.User;
import com.fiap.hackathon.domain.user.enumerated.UserTypeEnum;
import lombok.NonNull;

public final class UserCreateUseCase {

    private final User user;

    public UserCreateUseCase(@NonNull User loggedUser, @NonNull UserTypeEnum userType, @NonNull City city, @NonNull UserPostRequestDTO userPostRequestDTO, @NonNull String passwordCryptoKey) {
        this.user = buildUser(userType, city, userPostRequestDTO, passwordCryptoKey);
    }

    public UserCreateUseCase(@NonNull UserTypeEnum userType, @NonNull City city, @NonNull UserPostRequestDTO userPostRequestDTO, @NonNull String passwordCryptoKey) {
        this.user = buildUser(userType, city, userPostRequestDTO, passwordCryptoKey);
    }

    private User buildUser(UserTypeEnum userType, City city, UserPostRequestDTO userPostRequestDTO, String passwordCryptoKey) {
        return new User(
                userPostRequestDTO.getName(),
                userPostRequestDTO.getEmail(),
                userPostRequestDTO.getLogin(),
                passwordCryptoKey,
                userPostRequestDTO.getPassword(),
                userType,
                new Address(
                        userPostRequestDTO.getAddress().getDescription(),
                        userPostRequestDTO.getAddress().getNumber(),
                        userPostRequestDTO.getAddress().getComplement(),
                        userPostRequestDTO.getAddress().getNeighborhood(),
                        userPostRequestDTO.getAddress().getCep(),
                        userPostRequestDTO.getAddress().getPostalCode(),
                        city
                )
        );
    }

    public User getBuiltedUser() {
        return this.user;
    }
}