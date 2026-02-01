package com.fiap.hackathon.domain.answer.entity;

import com.fiap.hackathon.domain.answer.AnswerEntityListener;
import com.fiap.hackathon.domain.answer.enumerated.constraint.AnswerConstraint;
import com.fiap.hackathon.domain.formsubmission.entity.FormSubmission;
import com.fiap.hackathon.domain.question.entity.Question;
import com.fiap.hackathon.domain.question.enumerated.VisitationAlternativeEnum;
import com.fiap.hackathon.domain.user.entity.User;
import com.fiap.hackathon.global.audit.Audit;
import com.fiap.hackathon.global.constraint.ConstraintMapper;
import com.fiap.hackathon.global.exception.EntityCannotBeUpdatedException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.io.Serial;
import java.io.Serializable;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PROTECTED)
@Entity
@Table(name = "t_answer")
@SQLDelete(sql = "UPDATE t_answer SET deleted = true WHERE id = ?")
@SQLRestriction(value = "deleted = false")
@EntityListeners({ AnswerEntityListener.class })
@ConstraintMapper(constraintClass = AnswerConstraint.class)
public class Answer extends Audit implements Serializable {

    protected Answer() {}

    public Answer(@NonNull VisitationAlternativeEnum visitationAlternative, @NonNull String deliveredAnswer, @NonNull Question question, @NonNull User patient, @NonNull FormSubmission formSubmission) {
        if (formSubmission.getIsSubmitted()) {
            throw new EntityCannotBeUpdatedException("Não é possível responder um formulário que já foi submetido.");
        }
        this.setVisitationAlternative(visitationAlternative);
        this.setDeliveredAnswer(deliveredAnswer);
        this.setQuestion(question);
        this.setPatient(patient);
        this.setFormSubmission(formSubmission);
    }

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "SQ_ANSWER")
    @SequenceGenerator(name = "SQ_ANSWER", sequenceName = "SQ_ANSWER", schema = "public", allocationSize = 1)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "visitation_option", nullable = false)
    @Enumerated(EnumType.STRING)
    private VisitationAlternativeEnum visitationAlternative;

    @Column(name = "delivered_answer", nullable = false)
    private String deliveredAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_question", nullable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_patient", nullable = false)
    private User patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_form_submission", nullable = false)
    private FormSubmission formSubmission;

    @Transient
    private transient Answer answerSavedState;

    public void saveState(Answer answerSavedState) {
        this.answerSavedState = answerSavedState;
    }
}