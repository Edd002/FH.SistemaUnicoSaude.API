package com.fiap.hackathon.domain.formtemplate;

import com.fiap.hackathon.domain.formtemplate.entity.FormTemplate;
import com.fiap.hackathon.global.base.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFormTemplateRepository extends IBaseRepository<FormTemplate> {
}