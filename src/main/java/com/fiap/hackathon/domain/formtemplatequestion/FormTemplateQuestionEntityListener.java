package com.fiap.hackathon.domain.formtemplatequestion;

import com.fiap.hackathon.domain.formtemplatequestion.entity.FormTemplateQuestion;
import jakarta.persistence.PostLoad;
import org.apache.commons.lang3.SerializationUtils;

public class FormTemplateQuestionEntityListener {

    @PostLoad
    public void postLoad(FormTemplateQuestion formTemplateQuestionEntity) {
        formTemplateQuestionEntity.saveState(SerializationUtils.clone(formTemplateQuestionEntity));
    }
}