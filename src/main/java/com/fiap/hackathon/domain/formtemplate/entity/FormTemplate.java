package com.fiap.hackathon.domain.formtemplate.entity;

import com.fiap.hackathon.domain.formsubmission.entity.FormSubmission;
import com.fiap.hackathon.domain.formtemplate.FormTemplateEntityListener;
import com.fiap.hackathon.domain.formtemplate.enumerated.constraint.FormTemplateConstraint;
import com.fiap.hackathon.domain.formtemplatequestion.entity.FormTemplateQuestion;
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
import java.util.List;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PROTECTED)
@Entity
@Table(name = "t_form_template")
@SQLDelete(sql = "UPDATE t_form_template SET deleted = true WHERE id = ?")
@SQLRestriction(value = "deleted = false")
@EntityListeners({ FormTemplateEntityListener.class })
@ConstraintMapper(constraintClass = FormTemplateConstraint.class)
public class FormTemplate extends Audit implements Serializable {

    protected FormTemplate() {}

    public FormTemplate(@NonNull String name, @NonNull String description, @NonNull String professionalCns, @NonNull String cbo, @NonNull String cnes, @NonNull String ine, @NonNull Boolean isActive) {
        this.setName(name);
        this.setDescription(description);
        this.setProfessionalCns(professionalCns);
        this.setCbo(cbo);
        this.setCnes(cnes);
        this.setIne(ine);
        this.setIsActive(isActive);
    }

    public FormTemplate rebuild(@NonNull String name, @NonNull String description, @NonNull String professionalCns, @NonNull String cbo, @NonNull String cnes, @NonNull String ine, @NonNull Boolean isActive) {
        this.setName(name);
        this.setDescription(description);
        this.setProfessionalCns(professionalCns);
        this.setCbo(cbo);
        this.setCnes(cnes);
        this.setIne(ine);
        this.setIsActive(isActive);
        return this;
    }

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "SQ_FORM_TEMPLATE")
    @SequenceGenerator(name = "SQ_FORM_TEMPLATE", sequenceName = "SQ_FORM_TEMPLATE", schema = "public", allocationSize = 1)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "professional_cns", nullable = false)
    private String professionalCns;

    @Column(name = "cbo", nullable = false)
    private String cbo;

    @Column(name = "cnes", nullable = false)
    private String cnes;

    @Column(name = "ine", nullable = false)
    private String ine;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = Boolean.TRUE;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "formTemplate", cascade = { CascadeType.REMOVE })
    private List<FormTemplateQuestion> formTemplateQuestions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "formTemplate")
    private List<FormSubmission> formSubmissions;

    @Transient
    private transient FormTemplate formTemplateSavedState;

    public void saveState(FormTemplate formTemplateSavedState) {
        this.formTemplateSavedState = formTemplateSavedState;
    }
}