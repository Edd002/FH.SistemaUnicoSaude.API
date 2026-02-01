package com.fiap.hackathon.domain.answer;

import com.fiap.hackathon.domain.answer.dto.AnswerRegisterPatchRequestDTO;
import com.fiap.hackathon.domain.answer.dto.AnswerReplyPatchRequestDTO;
import com.fiap.hackathon.domain.answer.entity.Answer;
import com.fiap.hackathon.global.base.BaseServiceGateway;
import com.fiap.hackathon.global.search.builder.PageableBuilder;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceGateway extends BaseServiceGateway<IAnswerRepository, Answer> {

    private final PageableBuilder pageableBuilder;
    private final ModelMapper modelMapperPresenter;

    @Autowired
    public AnswerServiceGateway(IAnswerRepository answerRepository, PageableBuilder pageableBuilder, ModelMapper modelMapperPresenter) {
        this.pageableBuilder = pageableBuilder;
        this.modelMapperPresenter = modelMapperPresenter;
    }

    @Transactional
    public void registerAnswer(AnswerRegisterPatchRequestDTO answerRegisterPatchRequestDTO) {
    }

    @Transactional
    public void replyAnswer(AnswerReplyPatchRequestDTO answerReplyPatchRequestDTO) {
    }
}