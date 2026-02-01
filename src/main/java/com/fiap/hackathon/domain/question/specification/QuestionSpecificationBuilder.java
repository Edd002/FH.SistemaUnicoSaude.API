package com.fiap.hackathon.domain.question.specification;

import com.fiap.hackathon.domain.question.dto.QuestionGetFilter;
import com.fiap.hackathon.domain.question.entity.Question;
import com.fiap.hackathon.global.search.enumerated.FetchDeletedEnum;
import com.fiap.hackathon.global.search.enumerated.SearchOperationEnum;
import com.fiap.hackathon.global.search.specification.BasicSpecificationBuilder;
import com.fiap.hackathon.global.search.specification.SearchCriteria;
import com.fiap.hackathon.global.util.ValidationUtil;
import org.springframework.stereotype.Component;

@Component
public class QuestionSpecificationBuilder extends BasicSpecificationBuilder<Question, QuestionSpecification, QuestionGetFilter> {

    @Override
    protected void initParams(QuestionGetFilter filter) {
        if (ValidationUtil.isNotBlank(filter.getTitle())) {
            where("title", SearchOperationEnum.LIKE, filter.getTitle());
        }

        if (ValidationUtil.isNotBlank(filter.getDescription())) {
            where("description", SearchOperationEnum.LIKE, filter.getDescription());
        }

        if (ValidationUtil.isNotNull(filter.getTopic())) {
            where("topic", SearchOperationEnum.EQUAL, filter.getTopic());
        }

        if (ValidationUtil.isNotNull(filter.getType())) {
            where("type", SearchOperationEnum.EQUAL, filter.getType());
        }
    }

    @Override
    protected QuestionSpecification buildSpecification(String key, SearchOperationEnum operation, Object value) {
        return new QuestionSpecification(new SearchCriteria(key, operation, value));
    }

    @Override
    protected QuestionSpecification buildSpecification(String key, SearchOperationEnum operation, Object value, Object param) {
        return new QuestionSpecification(new SearchCriteria(key, operation, value, param));
    }

    @Override
    protected FetchDeletedEnum showDeleted() {
        return FetchDeletedEnum.FETCH_ALL;
    }
}