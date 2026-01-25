package com.fiap.hackathon.domain.questionnairequestion;

import com.fiap.hackathon.domain.questionnairequestion.entity.QuestionnaireQuestion;
import jakarta.persistence.PostLoad;
import org.apache.commons.lang3.SerializationUtils;

public class QuestionnaireQuestionEntityListener {

    @PostLoad
    public void postLoad(QuestionnaireQuestion questionnaireQuestionEntity) {
        questionnaireQuestionEntity.saveState(SerializationUtils.clone(questionnaireQuestionEntity));
    }
}