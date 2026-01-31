package com.fiap.hackathon.domain.formsubmission;

import com.fiap.hackathon.domain.formsubmission.entity.FormSubmission;
import jakarta.persistence.PostLoad;
import org.apache.commons.lang3.SerializationUtils;

public class FormSubmissionEntityListener {

    @PostLoad
    public void postLoad(FormSubmission formSubmissionEntity) {
        formSubmissionEntity.saveState(SerializationUtils.clone(formSubmissionEntity));
    }
}