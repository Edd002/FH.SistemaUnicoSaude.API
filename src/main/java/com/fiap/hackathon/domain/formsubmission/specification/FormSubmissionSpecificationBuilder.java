package com.fiap.hackathon.domain.formsubmission.specification;

import com.fiap.hackathon.domain.formsubmission.dto.FormSubmissionGetFilter;
import com.fiap.hackathon.domain.formsubmission.entity.FormSubmission;
import com.fiap.hackathon.global.search.enumerated.FetchDeletedEnum;
import com.fiap.hackathon.global.search.enumerated.SearchOperationEnum;
import com.fiap.hackathon.global.search.specification.BasicSpecificationBuilder;
import com.fiap.hackathon.global.search.specification.SearchCriteria;
import com.fiap.hackathon.global.util.ValidationUtil;
import org.springframework.stereotype.Component;

@Component
public class FormSubmissionSpecificationBuilder extends BasicSpecificationBuilder<FormSubmission, FormSubmissionSpecification, FormSubmissionGetFilter> {

    @Override
    protected void initParams(FormSubmissionGetFilter filter) {
        if (ValidationUtil.isNotNull(filter.getIsAnswered())) {
            where("isAnswered", SearchOperationEnum.EQUAL, filter.getIsAnswered());
        }

        if (ValidationUtil.isNotNull(filter.getCollectedAt())) {
            where("collectedAt", SearchOperationEnum.EQUAL, filter.getCollectedAt());
        }

        if (ValidationUtil.isNotNull(filter.getSyncedAt())) {
            where("syncedAt", SearchOperationEnum.EQUAL, filter.getSyncedAt());
        }

        if (ValidationUtil.isNotBlank(filter.getGeneralObservation())) {
            where("generalObservation", SearchOperationEnum.LIKE, filter.getGeneralObservation());
        }

        if (ValidationUtil.isNotBlank(filter.getHashIdFormTemplate())) {
            where("formTemplate.hashId", SearchOperationEnum.EQUAL, filter.getHashIdFormTemplate());
        }
    }

    @Override
    protected FormSubmissionSpecification buildSpecification(String key, SearchOperationEnum operation, Object value) {
        return new FormSubmissionSpecification(new SearchCriteria(key, operation, value));
    }

    @Override
    protected FormSubmissionSpecification buildSpecification(String key, SearchOperationEnum operation, Object value, Object param) {
        return new FormSubmissionSpecification(new SearchCriteria(key, operation, value, param));
    }

    @Override
    protected FetchDeletedEnum showDeleted() {
        return FetchDeletedEnum.FETCH_ALL;
    }
}