package com.fiap.hackathon.domain.state;

import com.fiap.hackathon.domain.state.entity.State;
import jakarta.persistence.PostLoad;
import org.apache.commons.lang3.SerializationUtils;

public final class StateEntityListener {

    @PostLoad
    public void postLoad(State stateEntity) {
        stateEntity.saveState(SerializationUtils.clone(stateEntity));
    }
}