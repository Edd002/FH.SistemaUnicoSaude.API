package com.fiap.hackathon.domain.loadtable.usecase;

import com.fiap.hackathon.domain.loadtable.entity.LoadTable;
import com.fiap.hackathon.global.util.ValidationUtil;

public final class LoadTableCreateUseCase {

    private final LoadTable loadTable;

    public LoadTableCreateUseCase(LoadTable loadTable, String entityName) {
        this.loadTable = ValidationUtil.isNotNull(loadTable) ? loadTable : new LoadTable(entityName);
    }

    public LoadTable getBuiltedLoadTable() {
        return this.loadTable;
    }
}