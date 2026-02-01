package com.fiap.hackathon.domain.user;

import com.fiap.hackathon.domain.city.CityServiceGateway;
import com.fiap.hackathon.domain.city.entity.City;
import com.fiap.hackathon.domain.jwt.JwtServiceGateway;
import com.fiap.hackathon.domain.user.authuser.AuthUserContextHolder;
import com.fiap.hackathon.domain.user.dto.*;
import com.fiap.hackathon.domain.user.entity.User;
import com.fiap.hackathon.domain.user.enumerated.UserTypeEnum;
import com.fiap.hackathon.domain.user.specification.UserSpecificationBuilder;
import com.fiap.hackathon.domain.user.usecase.*;
import com.fiap.hackathon.global.base.BaseServiceGateway;
import com.fiap.hackathon.global.exception.EntityNotFoundException;
import com.fiap.hackathon.global.search.builder.PageableBuilder;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceGateway extends BaseServiceGateway<IUserRepository, User> {

    @Value("${crypto.key}")
    private String cryptoKey;

    private final IUserRepository userRepository;
    private final JwtServiceGateway jwtServiceGateway;
    private final CityServiceGateway cityServiceGateway;
    private final PageableBuilder pageableBuilder;
    private final ModelMapper modelMapperPresenter;

    @Autowired
    public UserServiceGateway(IUserRepository userRepository, JwtServiceGateway jwtServiceGateway, CityServiceGateway cityServiceGateway, PageableBuilder pageableBuilder, ModelMapper modelMapperPresenter) {
        this.userRepository = userRepository;
        this.jwtServiceGateway = jwtServiceGateway;
        this.cityServiceGateway = cityServiceGateway;
        this.pageableBuilder = pageableBuilder;
        this.modelMapperPresenter = modelMapperPresenter;
    }

    @Transactional
    public UserResponseDTO create(UserPostRequestDTO userPostRequestDTO) {
        City city = cityServiceGateway.findByHashId(userPostRequestDTO.getAddress().getHashIdCity());
        User newUser = AuthUserContextHolder.getAuthUserIfExists()
                .map(loggedUser -> new UserCreateUseCase(loggedUser, UserTypeEnum.valueOf(userPostRequestDTO.getType()), city, userPostRequestDTO, cryptoKey).getBuiltedUser())
                .orElseGet(() -> new UserCreateUseCase(UserTypeEnum.valueOf(userPostRequestDTO.getType()), city, userPostRequestDTO, cryptoKey).getBuiltedUser());
        return modelMapperPresenter.map(save(newUser), UserResponseDTO.class);
    }

    @Transactional
    public UserResponseDTO update(UserPutRequestDTO userPutRequestDTO) {
        City city = cityServiceGateway.findByHashId(userPutRequestDTO.getAddress().getHashIdCity());
        User updatedUser = new UserUpdateUseCase(AuthUserContextHolder.getAuthUser(), UserTypeEnum.valueOf(userPutRequestDTO.getType()), city, userPutRequestDTO).getRebuiltedUser();
        return modelMapperPresenter.map(save(updatedUser), UserResponseDTO.class);
    }

    @Transactional
    public void updateLogin(UserUpdateLoginPatchRequestDTO userUpdateLoginPatchRequestDTO) {
        User updatedLoginUser = save(new UserUpdateLoginUseCase(AuthUserContextHolder.getAuthUser(), userUpdateLoginPatchRequestDTO).getBuiltedUser());
        jwtServiceGateway.invalidate(updatedLoginUser);
    }

    @Transactional
    public void updatePassword(UserUpdatePasswordPatchRequestDTO userUpdatePasswordPatchRequestDTO) {
        save(new UserUpdatePasswordUseCase(AuthUserContextHolder.getAuthUser(), userUpdatePasswordPatchRequestDTO, cryptoKey).getBuiltedUser());
    }

    @Transactional
    public Page<UserResponseDTO> find(UserGetFilter filter) {
        Pageable pageable = pageableBuilder.build(filter);
        Optional<Specification<User>> specification = new UserSpecificationBuilder().build(filter);
        return specification
                .map(spec -> findAll(spec, pageable))
                .orElseGet(() -> new PageImpl<>(new ArrayList<>()))
                .map(user -> modelMapperPresenter.map(user, UserResponseDTO.class));
    }

    @Transactional
    public UserResponseDTO find() {
        return modelMapperPresenter.map(AuthUserContextHolder.getAuthUser(), UserResponseDTO.class);
    }

    @Transactional
    public void delete() {
        User loggedUser = AuthUserContextHolder.getAuthUser();
        if (new UserCheckForDeleteUseCase(loggedUser).isAllowedToDelete()) {
            delete(loggedUser);
            SecurityContextHolder.clearContext();
        }
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(() -> new EntityNotFoundException(String.format("O usuário com o login %s não foi encontrado.", login)));
    }

    @Override
    public User findByHashId(String hashId) {
        return super.findByHashId(hashId, String.format("O usuário com o hash id %s não foi encontrado.", hashId));
    }

    public User findByHashIdAndUserType(String hashId,  UserTypeEnum userType) {
        return userRepository.findByHashIdAndUserType(hashId, userType).orElseThrow(() -> new EntityNotFoundException(String.format("O usuário de tipo %s com o hash id %s não foi encontrado.", userType.getDescription(), hashId)));
    }
}