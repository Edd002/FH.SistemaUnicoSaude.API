package com.fiap.hackathon.domain.question;

import com.fiap.hackathon.domain.question.entity.Question;
import com.fiap.hackathon.global.base.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionRepository extends IBaseRepository<Question> {
}