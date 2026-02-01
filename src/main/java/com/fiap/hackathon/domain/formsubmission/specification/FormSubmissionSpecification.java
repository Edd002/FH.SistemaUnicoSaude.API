package com.fiap.hackathon.domain.formsubmission.specification;

import com.fiap.hackathon.domain.formsubmission.entity.FormSubmission;
import com.fiap.hackathon.global.search.specification.BasicSpecification;
import com.fiap.hackathon.global.search.specification.SearchCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serial;

public class FormSubmissionSpecification extends BasicSpecification<FormSubmission> implements Specification<FormSubmission> {

    @Serial
    private static final long serialVersionUID = 1L;

    public FormSubmissionSpecification(SearchCriteria searchCriteria) {
        super(searchCriteria);
    }

    @Override
    public Predicate toPredicate(@NonNull Root<FormSubmission> root, CriteriaQuery<?> query, @NonNull CriteriaBuilder builder) {
        return super.genericPredicate(root, builder);
    }
}