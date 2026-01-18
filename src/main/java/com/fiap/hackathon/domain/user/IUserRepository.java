package com.fiap.hackathon.domain.user;

import com.fiap.hackathon.domain.user.entity.User;
import com.fiap.hackathon.global.base.IBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends IBaseRepository<User> {

    Optional<User> findByLogin(String login);
}