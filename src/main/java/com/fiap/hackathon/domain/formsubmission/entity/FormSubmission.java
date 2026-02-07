package com.fiap.hackathon.domain.formsubmission.entity;

import com.fiap.hackathon.domain.answer.entity.Answer;
import com.fiap.hackathon.domain.formsubmission.FormSubmissionEntityListener;
import com.fiap.hackathon.domain.formsubmission.enumerated.constraint.FormSubmissionConstraint;
import com.fiap.hackathon.domain.formtemplate.entity.FormTemplate;
import com.fiap.hackathon.domain.user.entity.User;
import com.fiap.hackathon.global.audit.Audit;
import com.fiap.hackathon.global.constraint.ConstraintMapper;
import com.fiap.hackathon.global.exception.EntityCannotBeUpdatedException;
import com.fiap.hackathon.global.util.ValidationUtil;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PROTECTED)
@Entity
@Table(name = "t_form_submission")
@SQLDelete(sql = "UPDATE t_form_submission SET deleted = true WHERE id = ?")
@SQLRestriction(value = "deleted = false")
@EntityListeners({FormSubmissionEntityListener.class})
@ConstraintMapper(constraintClass = FormSubmissionConstraint.class)
public class FormSubmission extends Audit implements Serializable {

    protected FormSubmission() {}

    public FormSubmission(@NonNull String generalObservation, @NonNull FormTemplate formTemplate, @NonNull User healthProfessional) {
        this.setIsSubmitted(Boolean.FALSE);
        this.setGeneralObservation(generalObservation);
        this.setFormTemplate(formTemplate);
        this.setHealthProfessional(healthProfessional);
    }

    public FormSubmission rebuild(String generalObservation) {
        if (this.isSubmitted) {
            throw new EntityCannotBeUpdatedException("A submissão do formulário já foi concluída.");
        }
        if (ValidationUtil.isNotBlank(generalObservation)) {
            this.setGeneralObservation(generalObservation);
        }
        this.setIsSubmitted(Boolean.TRUE);
        this.setSubmittedAt(new Date());
        return this;
    }

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "SQ_FORM_SUBMISSION")
    @SequenceGenerator(name = "SQ_FORM_SUBMISSION", sequenceName = "SQ_FORM_SUBMISSION", schema = "public", allocationSize = 1)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "is_submitted", nullable = false)
    private Boolean isSubmitted = Boolean.FALSE;

    @Column(name = "submitted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date submittedAt = new Date();

    @Column(name = "general_observation")
    private String generalObservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_form_template", nullable = false)
    private FormTemplate formTemplate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_health_professional", nullable = false)
    private User healthProfessional;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "formSubmission", cascade = { CascadeType.REMOVE })
    private List<Answer> answers;

    @Transient
    private transient FormSubmission formTemplateSavedState;

    public void saveState(FormSubmission formTemplateSavedStateSavedState) {
        this.formTemplateSavedState = formTemplateSavedStateSavedState;
    }
}