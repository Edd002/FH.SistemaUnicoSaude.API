package com.fiap.hackathon.domain.answer.specification;

import com.fiap.hackathon.domain.answer.dto.AnswerGetFilter;
import com.fiap.hackathon.domain.answer.entity.Answer;
import com.fiap.hackathon.global.search.enumerated.FetchDeletedEnum;
import com.fiap.hackathon.global.search.enumerated.SearchOperationEnum;
import com.fiap.hackathon.global.search.specification.BasicSpecificationBuilder;
import com.fiap.hackathon.global.search.specification.SearchCriteria;
import com.fiap.hackathon.global.util.ValidationUtil;
import org.springframework.stereotype.Component;

@Component
public class AnswerSpecificationBuilder extends BasicSpecificationBuilder<Answer, AnswerSpecification, AnswerGetFilter> {

    @Override
    protected void initParams(AnswerGetFilter filter) {
        if (ValidationUtil.isNotNull(filter.getVisitationAlternative())) {
            where("visitationAlternative", SearchOperationEnum.EQUAL, filter.getVisitationAlternative());
        }

        if (ValidationUtil.isNotBlank(filter.getDeliveredAnswer())) {
            where("deliveredAnswer", SearchOperationEnum.LIKE, filter.getDeliveredAnswer());
        }

        if (ValidationUtil.isNotBlank(filter.getHashIdQuestion())) {
            where("question.hashId", SearchOperationEnum.EQUAL, filter.getHashIdQuestion());
        }

        if (ValidationUtil.isNotBlank(filter.getHashIdPatient())) {
            where("patient.hashId", SearchOperationEnum.EQUAL, filter.getHashIdPatient());
        }

        if (ValidationUtil.isNotBlank(filter.getHashIdFormSubmission())) {
            where("formSubmission.hashId", SearchOperationEnum.EQUAL, filter.getHashIdFormSubmission());
        }
    }

    @Override
    protected AnswerSpecification buildSpecification(String key, SearchOperationEnum operation, Object value) {
        return new AnswerSpecification(new SearchCriteria(key, operation, value));
    }

    @Override
    protected AnswerSpecification buildSpecification(String key, SearchOperationEnum operation, Object value, Object param) {
        return new AnswerSpecification(new SearchCriteria(key, operation, value, param));
    }

    @Override
    protected FetchDeletedEnum showDeleted() {
        return FetchDeletedEnum.FETCH_ALL;
    }
}