package com.fiap.hackathon.domain.answer.entity;

import com.fiap.hackathon.domain.question.entity.Question;
import com.fiap.hackathon.domain.question.enumerated.QuestionAlternativeEnum;
import com.fiap.hackathon.domain.questionnaireuser.entity.QuestionnaireUser;
import com.fiap.hackathon.domain.user.UserEntityListener;
import com.fiap.hackathon.domain.user.enumerated.constraint.UserConstraint;
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
@Table(name = "t_answer")
@SQLDelete(sql = "UPDATE t_user SET deleted = true WHERE id = ?")
@SQLRestriction(value = "deleted = false")
@EntityListeners({ UserEntityListener.class })
@ConstraintMapper(constraintClass = UserConstraint.class)
public class Answer extends Audit implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "SQ_ANSWER")
    @SequenceGenerator(name = "SQ_ANSWER", sequenceName = "SQ_ANSWER", schema = "public", allocationSize = 1)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "answered_alternative", nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionAlternativeEnum answeredAlternative;

    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE })
    @JoinColumn(name = "fk_question", nullable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_questionnaire_user", nullable = false)
    private QuestionnaireUser questionnaireUser;

    @Transient
    private transient Answer answerQuestionnaireSavedState;

    public void saveState(Answer answerQuestionnaireSavedState) {
        this.answerQuestionnaireSavedState = answerQuestionnaireSavedState;
    }
}