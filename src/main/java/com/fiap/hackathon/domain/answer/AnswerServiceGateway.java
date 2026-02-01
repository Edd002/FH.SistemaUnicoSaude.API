package com.fiap.hackathon.domain.answer;

import com.fiap.hackathon.domain.answer.dto.AnswerGetFilter;
import com.fiap.hackathon.domain.answer.dto.AnswerRegisterPatchRequestDTO;
import com.fiap.hackathon.domain.answer.dto.AnswerReplyPatchRequestDTO;
import com.fiap.hackathon.domain.answer.dto.AnswerResponseDTO;
import com.fiap.hackathon.domain.answer.entity.Answer;
import com.fiap.hackathon.domain.answer.specification.AnswerSpecificationBuilder;
import com.fiap.hackathon.domain.answer.usecase.AnswerCreateUseCase;
import com.fiap.hackathon.domain.formsubmission.FormSubmissionServiceGateway;
import com.fiap.hackathon.domain.formsubmission.entity.FormSubmission;
import com.fiap.hackathon.domain.question.QuestionServiceGateway;
import com.fiap.hackathon.domain.question.entity.Question;
import com.fiap.hackathon.domain.user.UserServiceGateway;
import com.fiap.hackathon.domain.user.authuser.AuthUserContextHolder;
import com.fiap.hackathon.domain.user.entity.User;
import com.fiap.hackathon.domain.user.enumerated.UserTypeEnum;
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
public class AnswerServiceGateway extends BaseServiceGateway<IAnswerRepository, Answer> {

    private final PageableBuilder pageableBuilder;
    private final ModelMapper modelMapperPresenter;
    private final QuestionServiceGateway questionServiceGateway;
    private final UserServiceGateway userServiceGateway;
    private final FormSubmissionServiceGateway formSubmissionServiceGateway;

    @Autowired
    public AnswerServiceGateway(IAnswerRepository answerRepository, PageableBuilder pageableBuilder, ModelMapper modelMapperPresenter, QuestionServiceGateway questionServiceGateway, UserServiceGateway userServiceGateway, FormSubmissionServiceGateway formSubmissionServiceGateway) {
        this.pageableBuilder = pageableBuilder;
        this.modelMapperPresenter = modelMapperPresenter;
        this.questionServiceGateway = questionServiceGateway;
        this.userServiceGateway = userServiceGateway;
        this.formSubmissionServiceGateway = formSubmissionServiceGateway;
    }

    @Transactional
    public AnswerResponseDTO registerAnswer(AnswerRegisterPatchRequestDTO answerRegisterPatchRequestDTO) {
        Question existingQuestion = questionServiceGateway.findByHashId(answerRegisterPatchRequestDTO.getHashIdQuestion());
        User existingUserPatient = userServiceGateway.findByHashIdAndUserType(answerRegisterPatchRequestDTO.getHashIdPatient(), UserTypeEnum.PATIENT);
        FormSubmission existingFormSubmission = formSubmissionServiceGateway.findByHashId(answerRegisterPatchRequestDTO.getHashIdFormSubmission());
        Answer newAnswer = save(new AnswerCreateUseCase(existingQuestion, existingUserPatient, existingFormSubmission, answerRegisterPatchRequestDTO).getBuiltedAnswer());
        return modelMapperPresenter.map(newAnswer, AnswerResponseDTO.class);
    }

    @Transactional
    public AnswerResponseDTO replyAnswer(AnswerReplyPatchRequestDTO answerReplyPatchRequestDTO) {
        Question existingQuestion = questionServiceGateway.findByHashId(answerReplyPatchRequestDTO.getHashIdQuestion());
        User existingUserPatient = AuthUserContextHolder.getAuthUser();
        FormSubmission existingFormSubmission = formSubmissionServiceGateway.findByHashId(answerReplyPatchRequestDTO.getHashIdFormSubmission());
        Answer newAnswer = save(new AnswerCreateUseCase(existingQuestion, existingUserPatient, existingFormSubmission, answerReplyPatchRequestDTO).getBuiltedAnswer());
        return modelMapperPresenter.map(newAnswer, AnswerResponseDTO.class);
    }

    @Transactional
    public Page<AnswerResponseDTO> find(AnswerGetFilter filter) {
        Pageable pageable = pageableBuilder.build(filter);
        Optional<Specification<Answer>> specification = new AnswerSpecificationBuilder().build(filter);
        return specification
                .map(spec -> findAll(spec, pageable))
                .orElseGet(() -> new PageImpl<>(new ArrayList<>()))
                .map(answer -> modelMapperPresenter.map(answer, AnswerResponseDTO.class));
    }

    @Transactional
    public AnswerResponseDTO find(String hashId) {
        return modelMapperPresenter.map(this.findByHashId(hashId), AnswerResponseDTO.class);
    }

    @Override
    public Answer findByHashId(String hashId) {
        return super.findByHashId(hashId, String.format("A resposta com o hash id %s não foi encontrada.", hashId));
    }
}