package com.fiap.hackathon.domain.question;

import com.fiap.hackathon.domain.question.entity.Question;
import com.fiap.hackathon.global.base.IBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IQuestionRepository extends IBaseRepository<Question> {
    Optional<Question> findByTitle(String title);
}