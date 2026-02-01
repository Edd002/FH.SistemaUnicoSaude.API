package com.fiap.hackathon.domain.formtemplatequestion.entity;

import com.fiap.hackathon.domain.question.entity.Question;
import com.fiap.hackathon.domain.formtemplate.entity.FormTemplate;
import com.fiap.hackathon.domain.formtemplatequestion.FormTemplateQuestionEntityListener;
import com.fiap.hackathon.domain.formtemplatequestion.enumerated.constraint.FormTemplateQuestionConstraint;
import com.fiap.hackathon.global.audit.Audit;
import com.fiap.hackathon.global.constraint.ConstraintMapper;
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
@Table(name = "t_form_template_question")
@SQLDelete(sql = "UPDATE t_form_template_question SET deleted = true WHERE id = ?")
@SQLRestriction(value = "deleted = false")
@EntityListeners({ FormTemplateQuestionEntityListener.class })
@ConstraintMapper(constraintClass = FormTemplateQuestionConstraint.class)
public class FormTemplateQuestion extends Audit implements Serializable {

    protected FormTemplateQuestion() {}

    public FormTemplateQuestion(@NonNull Question question) {
        this.setQuestion(question);
    }

    public FormTemplateQuestion rebuild(@NonNull FormTemplate formTemplate) {
        this.setFormTemplate(formTemplate);
        return this;
    }

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "SQ_FORM_TEMPLATE_QUESTION")
    @SequenceGenerator(name = "SQ_FORM_TEMPLATE_QUESTION", sequenceName = "SQ_FORM_TEMPLATE_QUESTION", schema = "public", allocationSize = 1)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_form_template", nullable = false)
    private FormTemplate formTemplate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_question", nullable = false)
    private Question question;

    @Transient
    private transient FormTemplateQuestion formTemplateQuestionSavedState;

    public void saveState(FormTemplateQuestion formTemplateQuestionSavedState) {
        this.formTemplateQuestionSavedState = formTemplateQuestionSavedState;
    }
}