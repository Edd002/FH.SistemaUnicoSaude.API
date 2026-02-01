package com.fiap.hackathon.domain.formsubmission;

import com.fiap.hackathon.domain.formsubmission.entity.FormSubmission;
import com.fiap.hackathon.global.base.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFormSubmissionRepository extends IBaseRepository<FormSubmission> {
}