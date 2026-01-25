package com.fiap.hackathon.domain.questionnaire;

import com.fiap.hackathon.domain.questionnaire.entity.Questionnaire;
import jakarta.persistence.PostLoad;
import org.apache.commons.lang3.SerializationUtils;

public class QuestionnaireEntityListener {

    @PostLoad
    public void postLoad(Questionnaire questionnaireEntity) {
        questionnaireEntity.saveState(SerializationUtils.clone(questionnaireEntity));
    }
}