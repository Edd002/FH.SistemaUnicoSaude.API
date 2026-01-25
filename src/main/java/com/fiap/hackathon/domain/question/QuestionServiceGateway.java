package com.fiap.hackathon.domain.question;

import com.fiap.hackathon.domain.question.dto.QuestionGetFilter;
import com.fiap.hackathon.domain.question.dto.QuestionPostRequestDTO;
import com.fiap.hackathon.domain.question.dto.QuestionPutRequestDTO;
import com.fiap.hackathon.domain.question.dto.QuestionResponseDTO;
import com.fiap.hackathon.domain.question.entity.Question;
import com.fiap.hackathon.domain.question.specification.QuestionSpecificationBuilder;
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
import java.util.Optional;

@Service
public class QuestionServiceGateway extends BaseServiceGateway<IQuestionRepository, Question> {

    private final PageableBuilder pageableBuilder;
    private final ModelMapper modelMapperPresenter;

    @Autowired
    public QuestionServiceGateway(IQuestionRepository questionRepository, PageableBuilder pageableBuilder, ModelMapper modelMapperPresenter) {
        this.pageableBuilder = pageableBuilder;
        this.modelMapperPresenter = modelMapperPresenter;
    }

    @Transactional
    public QuestionResponseDTO create(QuestionPostRequestDTO questionPostRequestDTO) {
        return null;
    }

    @Transactional
    public QuestionResponseDTO update(QuestionPutRequestDTO questionPutRequestDTO) {
        return null;
    }

    @Transactional
    public Page<QuestionResponseDTO> find(QuestionGetFilter filter) {
        Pageable pageable = pageableBuilder.build(filter);
        Optional<Specification<Question>> specification = new QuestionSpecificationBuilder().build(filter);
        return specification
                .map(spec -> findAll(spec, pageable))
                .orElseGet(() -> new PageImpl<>(new ArrayList<>()))
                .map(user -> modelMapperPresenter.map(user, QuestionResponseDTO.class));
    }

    @Transactional
    public void delete() {
    }

    @Transactional
    public QuestionResponseDTO find(String hashId) {
        return modelMapperPresenter.map(this.findByHashId(hashId), QuestionResponseDTO.class);
    }

    @Override
    public Question findByHashId(String hashId) {
        return super.findByHashId(hashId, String.format("A questão com o hash id %s não foi encontrada.", hashId));
    }
}