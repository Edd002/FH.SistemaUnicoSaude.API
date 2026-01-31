package com.fiap.hackathon.domain.formtemplate;

import com.fiap.hackathon.domain.formtemplate.entity.FormTemplate;
import jakarta.persistence.PostLoad;
import org.apache.commons.lang3.SerializationUtils;

public class FormTemplateEntityListener {

    @PostLoad
    public void postLoad(FormTemplate formTemplateEntity) {
        formTemplateEntity.saveState(SerializationUtils.clone(formTemplateEntity));
    }
}