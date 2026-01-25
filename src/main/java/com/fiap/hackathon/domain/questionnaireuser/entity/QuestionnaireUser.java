package com.fiap.hackathon.domain.questionnaireuser.entity;

import com.fiap.hackathon.domain.answer.entity.Answer;
import com.fiap.hackathon.domain.questionnaire.entity.Questionnaire;
import com.fiap.hackathon.domain.user.UserEntityListener;
import com.fiap.hackathon.domain.user.entity.User;
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
import java.util.List;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PROTECTED)
@Entity
@Table(name = "t_questionnaire_user")
@SQLDelete(sql = "UPDATE t_user SET deleted = true WHERE id = ?")
@SQLRestriction(value = "deleted = false")
@EntityListeners({UserEntityListener.class})
@ConstraintMapper(constraintClass = UserConstraint.class)
public class QuestionnaireUser extends Audit implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "SQ_QUESTIONNAIRE_USER")
    @SequenceGenerator(name = "SQ_QUESTIONNAIRE_USER", sequenceName = "SQ_QUESTIONNAIRE_USER", schema = "public", allocationSize = 1)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "answered", nullable = false)
    private Boolean answered = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_questionnaire", nullable = false)
    private Questionnaire questionnaire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user", nullable = false)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "answers")
    private List<Answer> answers;

    @Transient
    private transient QuestionnaireUser questionnaireUserSavedState;

    public void saveState(QuestionnaireUser questionnaireUserSavedState) {
        this.questionnaireUserSavedState = questionnaireUserSavedState;
    }
}