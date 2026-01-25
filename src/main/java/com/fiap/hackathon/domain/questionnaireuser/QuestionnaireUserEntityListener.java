package com.fiap.hackathon.domain.questionnaireuser;

import com.fiap.hackathon.domain.questionnaireuser.entity.QuestionnaireUser;
import jakarta.persistence.PostLoad;
import org.apache.commons.lang3.SerializationUtils;

public class QuestionnaireUserEntityListener {

    @PostLoad
    public void postLoad(QuestionnaireUser questionnaireUserEntity) {
        questionnaireUserEntity.saveState(SerializationUtils.clone(questionnaireUserEntity));
    }
}