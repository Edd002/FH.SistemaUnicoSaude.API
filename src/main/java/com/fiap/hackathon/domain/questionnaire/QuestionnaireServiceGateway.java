package com.fiap.hackathon.domain.questionnaire;

import com.fiap.hackathon.domain.question.dto.QuestionResponseDTO;
import com.fiap.hackathon.domain.questionnaire.dto.QuestionnaireGetFilter;
import com.fiap.hackathon.domain.questionnaire.dto.QuestionnaireResponseDTO;
import com.fiap.hackathon.domain.questionnaire.entity.Questionnaire;
import com.fiap.hackathon.domain.questionnaire.specification.QuestionnaireSpecificationBuilder;
import com.fiap.hackathon.global.base.BaseServiceGateway;
import com.fiap.hackathon.global.search.builder.PageableBuilder;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionnaireServiceGateway extends BaseServiceGateway<IQuestionnaireRepository, Questionnaire> {

    private final PageableBuilder pageableBuilder;
    private final ModelMapper modelMapperPresenter;

    @Autowired
    public QuestionnaireServiceGateway(IQuestionnaireRepository questionnaireRepository, PageableBuilder pageableBuilder, ModelMapper modelMapperPresenter) {
        this.pageableBuilder = pageableBuilder;
        this.modelMapperPresenter = modelMapperPresenter;
    }

    @Transactional
    public Page<QuestionnaireResponseDTO> find(QuestionnaireGetFilter filter) {
        Pageable pageable = pageableBuilder.build(filter);
        Optional<Specification<Questionnaire>> specification = new QuestionnaireSpecificationBuilder().build(filter);
        return specification
                .map(spec -> findAll(spec, pageable))
                .orElseGet(() -> new PageImpl<>(new ArrayList<>()))
                .map(user -> modelMapperPresenter.map(user, QuestionnaireResponseDTO.class));
    }

    @Transactional
    public QuestionnaireResponseDTO find(String hashId) {
        Questionnaire questionnaire = this.findByHashId(hashId);
        QuestionnaireResponseDTO dto = modelMapperPresenter.map(questionnaire, QuestionnaireResponseDTO.class);
        List<QuestionResponseDTO> questions = questionnaire.getQuestionnaireQuestions().stream()
                .map(qq -> modelMapperPresenter.map(qq.getQuestion(), QuestionResponseDTO.class))
                .collect(Collectors.toList());
        dto.setQuestions(questions);
        return dto;
    }

    @Override
    public Questionnaire findByHashId(String hashId) {
        return super.findByHashId(hashId, String.format("O questionário com o hash id %s não foi encontrado.", hashId));
    }
}