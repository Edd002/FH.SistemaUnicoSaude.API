package com.fiap.hackathon.domain.questionnaire.entity;

import com.fiap.hackathon.domain.questionnairequestion.entity.QuestionnaireQuestion;
import com.fiap.hackathon.domain.user.UserEntityListener;
import com.fiap.hackathon.domain.user.enumerated.constraint.UserConstraint;
import com.fiap.hackathon.domain.questionnaireuser.entity.QuestionnaireUser;
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
@Table(name = "t_questionnaire")
@SQLDelete(sql = "UPDATE t_user SET deleted = true WHERE id = ?")
@SQLRestriction(value = "deleted = false")
@EntityListeners({ UserEntityListener.class })
@ConstraintMapper(constraintClass = UserConstraint.class)
public class Questionnaire extends Audit implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "SQ_QUESTIONNAIRE")
    @SequenceGenerator(name = "SQ_QUESTIONNAIRE", sequenceName = "SQ_QUESTIONNAIRE", schema = "public", allocationSize = 1)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "questionnaire", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    private List<QuestionnaireQuestion> questionnaireQuestions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "questionnaire")
    private List<QuestionnaireUser> QuestionnaireUsers;

    @Transient
    private transient Questionnaire questionnaireSavedState;

    public void saveState(Questionnaire questionnaireSavedState) {
        this.questionnaireSavedState = questionnaireSavedState;
    }
}