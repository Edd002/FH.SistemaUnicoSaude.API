package com.fiap.hackathon.domain.questionnairequestion.entity;

import com.fiap.hackathon.domain.question.entity.Question;
import com.fiap.hackathon.domain.questionnaire.entity.Questionnaire;
import com.fiap.hackathon.domain.questionnairequestion.QuestionnaireQuestionEntityListener;
import com.fiap.hackathon.domain.questionnairequestion.enumerated.constraint.QuestionnaireQuestionConstraint;
import com.fiap.hackathon.global.audit.Audit;
import com.fiap.hackathon.global.constraint.ConstraintMapper;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.io.Serial;
import java.io.Serializable;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PROTECTED)
@Entity
@Table(name = "t_questionnaire_question")
@SQLDelete(sql = "UPDATE t_user SET deleted = true WHERE id = ?")
@SQLRestriction(value = "deleted = false")
@EntityListeners({ QuestionnaireQuestionEntityListener.class })
@ConstraintMapper(constraintClass = QuestionnaireQuestionConstraint.class)
public class QuestionnaireQuestion extends Audit implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "SQ_QUESTIONNAIRE_QUESTION")
    @SequenceGenerator(name = "SQ_QUESTIONNAIRE_QUESTION", sequenceName = "SQ_QUESTIONNAIRE_QUESTION", schema = "public", allocationSize = 1)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_questionnaire", nullable = false)
    private Questionnaire questionnaire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_question", nullable = false)
    private Question question;

    @Transient
    private transient QuestionnaireQuestion questionnaireQuestionSavedState;

    public void saveState(QuestionnaireQuestion questionnaireQuestionSavedState) {
        this.questionnaireQuestionSavedState = questionnaireQuestionSavedState;
    }
}