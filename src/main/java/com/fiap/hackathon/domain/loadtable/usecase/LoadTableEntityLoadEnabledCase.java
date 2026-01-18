package com.fiap.hackathon.domain.loadtable.usecase;

import com.fiap.hackathon.domain.loadtable.entity.LoadTable;
import com.fiap.hackathon.global.util.ValidationUtil;

public final class LoadTableEntityLoadEnabledCase {

    private final Boolean isEntityLoadEnabled;

    public LoadTableEntityLoadEnabledCase(LoadTable loadTable) {
        this.isEntityLoadEnabled = ValidationUtil.isNull(loadTable) || loadTable.getEntityLoadEnabled();
    }

    public Boolean isEntityLoadEnabled() {
        return this.isEntityLoadEnabled;
    }
}