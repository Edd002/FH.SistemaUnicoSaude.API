package com.fiap.hackathon.domain.questionnairequestion;

import com.fiap.hackathon.domain.questionnairequestion.entity.QuestionnaireQuestion;
import com.fiap.hackathon.global.base.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionnaireQuestionRepository extends IBaseRepository<QuestionnaireQuestion> {
}