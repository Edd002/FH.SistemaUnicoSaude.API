package com.fiap.hackathon.domain.question.entity;

import com.fiap.hackathon.domain.answer.entity.Answer;
import com.fiap.hackathon.domain.question.QuestionEntityListener;
import com.fiap.hackathon.domain.question.enumerated.QuestionTopicEnum;
import com.fiap.hackathon.domain.question.enumerated.QuestionTypeEnum;
import com.fiap.hackathon.domain.question.enumerated.constraint.QuestionConstraint;
import com.fiap.hackathon.domain.formtemplatequestion.entity.FormTemplateQuestion;
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
@Table(name = "t_question")
@SQLDelete(sql = "UPDATE t_question SET deleted = true WHERE id = ?")
@SQLRestriction(value = "deleted = false")
@EntityListeners({ QuestionEntityListener.class })
@ConstraintMapper(constraintClass = QuestionConstraint.class)
public class Question extends Audit implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "SQ_QUESTION")
    @SequenceGenerator(name = "SQ_QUESTION", sequenceName = "SQ_QUESTION", schema = "public", allocationSize = 1)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "topic", nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionTopicEnum topic;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionTypeEnum type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private List<FormTemplateQuestion> formTemplateQuestions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private List<Answer> answers;

    @Transient
    private transient Question questionSavedState;

    public void saveState(Question questionSavedState) {
        this.questionSavedState = questionSavedState;
    }
}