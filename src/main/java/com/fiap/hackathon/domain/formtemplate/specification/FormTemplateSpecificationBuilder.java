package com.fiap.hackathon.domain.formtemplate.specification;

import com.fiap.hackathon.domain.formtemplate.dto.FormTemplateGetFilter;
import com.fiap.hackathon.domain.formtemplate.entity.FormTemplate;
import com.fiap.hackathon.global.search.enumerated.FetchDeletedEnum;
import com.fiap.hackathon.global.search.enumerated.SearchOperationEnum;
import com.fiap.hackathon.global.search.specification.BasicSpecificationBuilder;
import com.fiap.hackathon.global.search.specification.SearchCriteria;
import com.fiap.hackathon.global.util.ValidationUtil;
import org.springframework.stereotype.Component;

@Component
public class FormTemplateSpecificationBuilder extends BasicSpecificationBuilder<FormTemplate, FormTemplateSpecification, FormTemplateGetFilter> {

    @Override
    protected void initParams(FormTemplateGetFilter filter) {
        if (ValidationUtil.isNotBlank(filter.getName())) {
            where("name", SearchOperationEnum.LIKE, filter.getName());
        }

        if (ValidationUtil.isNotBlank(filter.getDescription())) {
            where("description", SearchOperationEnum.LIKE, filter.getDescription());
        }

        if (ValidationUtil.isNotBlank(filter.getProfessionalCns())) {
            where("professionalCns", SearchOperationEnum.LIKE, filter.getProfessionalCns());
        }

        if (ValidationUtil.isNotBlank(filter.getCbo())) {
            where("cbo", SearchOperationEnum.EQUAL, filter.getCbo());
        }

        if (ValidationUtil.isNotBlank(filter.getCnes())) {
            where("cnes", SearchOperationEnum.EQUAL, filter.getCnes());
        }

        if (ValidationUtil.isNotBlank(filter.getIne())) {
            where("ine", SearchOperationEnum.EQUAL, filter.getIne());
        }
    }

    @Override
    protected FormTemplateSpecification buildSpecification(String key, SearchOperationEnum operation, Object value) {
        return new FormTemplateSpecification(new SearchCriteria(key, operation, value));
    }

    @Override
    protected FormTemplateSpecification buildSpecification(String key, SearchOperationEnum operation, Object value, Object param) {
        return new FormTemplateSpecification(new SearchCriteria(key, operation, value, param));
    }

    @Override
    protected FetchDeletedEnum showDeleted() {
        return FetchDeletedEnum.FETCH_ALL;
    }
}