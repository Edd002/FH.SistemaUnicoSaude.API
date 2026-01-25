package com.fiap.hackathon.domain.questionnaire;

import com.fiap.hackathon.domain.questionnaire.entity.Questionnaire;
import com.fiap.hackathon.global.base.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionnaireRepository extends IBaseRepository<Questionnaire> {
}