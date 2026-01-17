package com.fiap.hackathon.domain.state;

import com.fiap.hackathon.domain.state.entity.State;
import com.fiap.hackathon.global.base.BaseServiceGateway;
import org.springframework.stereotype.Service;

@Service
public class StateServiceGateway extends BaseServiceGateway<IStateRepository, State> {

    @Override
    public State findByHashId(String hashId) {
        return super.findByHashId(hashId, String.format("O estado com o hash id %s não foi encontrado.", hashId));
    }
}