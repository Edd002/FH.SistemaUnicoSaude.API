package com.fiap.hackathon.domain.answer;

import com.fiap.hackathon.domain.answer.entity.Answer;
import com.fiap.hackathon.global.base.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnswerRepository extends IBaseRepository<Answer> {
}