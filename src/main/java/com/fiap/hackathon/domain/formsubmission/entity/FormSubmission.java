package com.fiap.hackathon.domain.formsubmission.entity;

import com.fiap.hackathon.domain.answer.entity.Answer;
import com.fiap.hackathon.domain.formtemplate.entity.FormTemplate;
import com.fiap.hackathon.domain.formsubmission.FormSubmissionEntityListener;
import com.fiap.hackathon.domain.formsubmission.enumerated.constraint.FormSubmissionConstraint;
import com.fiap.hackathon.domain.user.entity.User;
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

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "SQ_FORM_SUBMISSION")
    @SequenceGenerator(name = "SQ_FORM_SUBMISSION", sequenceName = "SQ_FORM_SUBMISSION", schema = "public", allocationSize = 1)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "is_answered", nullable = false)
    private Boolean isAnswered = Boolean.FALSE;

    @Column(name = "collected_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date collectedAt = new Date();

    @Column(name = "synced_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date syncedAt;

    @Column(name = "general_observation")
    private String generalObservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_form_template", nullable = false)
    private FormTemplate formTemplate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_health_professional", nullable = false)
    private User healthProfessional;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "formSubmission")
    private List<Answer> answers;

    @Transient
    private transient FormSubmission formTemplateSavedState;

    public void saveState(FormSubmission formTemplateSavedStateSavedState) {
        this.formTemplateSavedState = formTemplateSavedStateSavedState;
    }
}