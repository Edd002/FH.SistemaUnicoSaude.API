package com.fiap.hackathon.domain.answer;

import com.fiap.hackathon.domain.answer.entity.Answer;
import jakarta.persistence.PostLoad;
import org.apache.commons.lang3.SerializationUtils;

public class AnswerEntityListener {

    @PostLoad
    public void postLoad(Answer answerEntity) {
        answerEntity.saveState(SerializationUtils.clone(answerEntity));
    }
}