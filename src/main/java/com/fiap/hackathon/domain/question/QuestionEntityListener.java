package com.fiap.hackathon.domain.question;

import com.fiap.hackathon.domain.question.entity.Question;
import jakarta.persistence.PostLoad;
import org.apache.commons.lang3.SerializationUtils;

public final class QuestionEntityListener {

    @PostLoad
    public void postLoad(Question questionEntity) {
        questionEntity.saveState(SerializationUtils.clone(questionEntity));
    }
}