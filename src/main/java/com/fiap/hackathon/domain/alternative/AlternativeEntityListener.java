package com.fiap.hackathon.domain.alternative;

import com.fiap.hackathon.domain.alternative.entity.Alternative;
import jakarta.persistence.PostLoad;
import org.apache.commons.lang3.SerializationUtils;

public class AlternativeEntityListener {

    @PostLoad
    public void postLoad(Alternative alternativeEntity) {
        alternativeEntity.saveState(SerializationUtils.clone(alternativeEntity));
    }
}