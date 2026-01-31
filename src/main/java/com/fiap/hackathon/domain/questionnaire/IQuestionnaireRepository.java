package com.fiap.hackathon.domain.questionnaire;

import com.fiap.hackathon.domain.questionnaire.entity.Questionnaire;
import com.fiap.hackathon.global.base.IBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IQuestionnaireRepository extends IBaseRepository<Questionnaire> {
    Optional<Questionnaire> findByName(String name);
}