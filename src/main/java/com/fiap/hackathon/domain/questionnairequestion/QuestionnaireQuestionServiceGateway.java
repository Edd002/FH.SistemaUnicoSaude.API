package com.fiap.hackathon.domain.questionnairequestion;

import com.fiap.hackathon.domain.question.IQuestionRepository;
import com.fiap.hackathon.domain.question.entity.Question;
import com.fiap.hackathon.domain.questionnaire.IQuestionnaireRepository;
import com.fiap.hackathon.domain.questionnaire.entity.Questionnaire;
import com.fiap.hackathon.domain.questionnairequestion.entity.QuestionnaireQuestion;
import com.fiap.hackathon.global.base.BaseServiceGateway;
import com.fiap.hackathon.global.exception.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionnaireQuestionServiceGateway extends BaseServiceGateway<IQuestionnaireQuestionRepository, QuestionnaireQuestion> {

    private final IQuestionnaireRepository questionnaireRepository;
    private final IQuestionRepository questionRepository;

    @Autowired
    public QuestionnaireQuestionServiceGateway(IQuestionnaireRepository questionnaireRepository, IQuestionRepository questionRepository) {
        this.questionnaireRepository = questionnaireRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    @Transactional
    public QuestionnaireQuestion save(QuestionnaireQuestion questionnaireQuestion) {
        Optional<Questionnaire> questionnaire = questionnaireRepository.findByName(questionnaireQuestion.getQuestionnaire().getName());
        Optional<Question> question = questionRepository.findByTitle(questionnaireQuestion.getQuestion().getTitle());

        if (questionnaire.isPresent() && question.isPresent()) {
            return super.save(new QuestionnaireQuestion(questionnaire.get(), question.get()));
        } else {
            throw new EntityNotFoundException("Questionnaire or Question not found for linking.");
        }
    }
}