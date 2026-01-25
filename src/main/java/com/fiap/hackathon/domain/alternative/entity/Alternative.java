package com.fiap.hackathon.domain.alternative.entity;

import com.fiap.hackathon.domain.alternative.enumerated.constraint.AlternativeConstraint;
import com.fiap.hackathon.domain.alternative.AlternativeEntityListener;
import com.fiap.hackathon.domain.alternative.enumerated.AlternativeEnum;
import com.fiap.hackathon.domain.answer.entity.Answer;
import com.fiap.hackathon.domain.question.entity.Question;
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
@Table(name = "t_alternative")
@SQLDelete(sql = "UPDATE t_alternative SET deleted = true WHERE id = ?")
@SQLRestriction(value = "deleted = false")
@EntityListeners({ AlternativeEntityListener.class })
@ConstraintMapper(constraintClass = AlternativeConstraint.class)
public class Alternative extends Audit implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "SQ_ALTERNATIVE")
    @SequenceGenerator(name = "SQ_ALTERNATIVE", sequenceName = "SQ_ALTERNATIVE", schema = "public", allocationSize = 1)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "option", nullable = false)
    @Enumerated(EnumType.STRING)
    private AlternativeEnum option;

    @Column(name = "body", nullable = false)
    private String body;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "correctAlternative")
    private Question questionCorrectAlternative;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "answeredAlternative")
    private List<Answer> answers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_question", nullable = false)
    private Question question;

    @Transient
    private transient Alternative alternativeQuestionnaireSavedState;

    public void saveState(Alternative alternativeQuestionnaireSavedState) {
        this.alternativeQuestionnaireSavedState = alternativeQuestionnaireSavedState;
    }
}