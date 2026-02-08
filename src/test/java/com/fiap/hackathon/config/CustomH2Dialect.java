package com.fiap.hackathon.config;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.type.BasicType;
import org.hibernate.type.StandardBasicTypes;

import java.util.Date;

public class CustomH2Dialect extends H2Dialect {

    @Override
    public void initializeFunctionRegistry(FunctionContributions functionContributions) {
        super.initializeFunctionRegistry(functionContributions);
        BasicType<Date> resultType = functionContributions.getTypeConfiguration().getBasicTypeRegistry().resolve(StandardBasicTypes.DATE);
        functionContributions.getFunctionRegistry().registerPattern("DATE", "FORMATDATETIME(?1, 'yyyy-MM-dd')", resultType);
    }
}