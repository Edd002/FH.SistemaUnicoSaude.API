package com.fiap.hackathon.domain.jwt;

import com.fiap.hackathon.domain.jwt.entity.Jwt;
import com.fiap.hackathon.global.base.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJwtRepository extends IBaseRepository<Jwt> {

    Jwt findByBearerToken(String bearerToken);
}