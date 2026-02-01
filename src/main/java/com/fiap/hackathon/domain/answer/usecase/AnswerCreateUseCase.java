package com.fiap.hackathon.domain.answer.usecase;

import com.fiap.hackathon.domain.answer.dto.AnswerRegisterPatchRequestDTO;
import com.fiap.hackathon.domain.answer.dto.AnswerReplyPatchRequestDTO;
import com.fiap.hackathon.domain.answer.entity.Answer;
import com.fiap.hackathon.domain.formsubmission.entity.FormSubmission;
import com.fiap.hackathon.domain.question.entity.Question;
import com.fiap.hackathon.domain.question.enumerated.VisitationAlternativeEnum;
import com.fiap.hackathon.domain.user.entity.User;
import lombok.NonNull;

public class AnswerCreateUseCase {

    private final Answer answer;

    public AnswerCreateUseCase(@NonNull Question existingQuestion, @NonNull User existingUserPatient, @NonNull FormSubmission existingFormSubmission, @NonNull AnswerRegisterPatchRequestDTO answerRegisterPatchRequestDTO) {
        this.answer = buildAnswer(existingQuestion, existingUserPatient, existingFormSubmission, answerRegisterPatchRequestDTO);
    }

    public AnswerCreateUseCase(@NonNull Question existingQuestion, @NonNull User existingUserPatient, @NonNull FormSubmission existingFormSubmission, @NonNull AnswerReplyPatchRequestDTO answerReplyPatchRequestDTO) {
        this.answer = buildAnswer(existingQuestion, existingUserPatient, existingFormSubmission, answerReplyPatchRequestDTO);
    }

    private Answer buildAnswer(Question existingQuestion, User existingUserPatient, FormSubmission existingFormSubmission, AnswerRegisterPatchRequestDTO answerRegisterPatchRequestDTO) {
        return new Answer(
                VisitationAlternativeEnum.valueOf(answerRegisterPatchRequestDTO.getVisitationAlternative()),
                answerRegisterPatchRequestDTO.getDeliveredAnswer(),
                existingQuestion,
                existingUserPatient,
                existingFormSubmission
        );
    }

    private Answer buildAnswer(Question existingQuestion, User existingUserPatient, FormSubmission existingFormSubmission, AnswerReplyPatchRequestDTO answerReplyPatchRequestDTO) {
        return new Answer(
                VisitationAlternativeEnum.valueOf(answerReplyPatchRequestDTO.getVisitationAlternative()),
                answerReplyPatchRequestDTO.getDeliveredAnswer(),
                existingQuestion,
                existingUserPatient,
                existingFormSubmission
        );
    }

    public Answer getBuiltedAnswer() {
        return this.answer;
    }
}