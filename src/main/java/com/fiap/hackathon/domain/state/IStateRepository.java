package com.fiap.hackathon.domain.state;

import com.fiap.hackathon.domain.state.entity.State;
import com.fiap.hackathon.global.base.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStateRepository extends IBaseRepository<State> {
}