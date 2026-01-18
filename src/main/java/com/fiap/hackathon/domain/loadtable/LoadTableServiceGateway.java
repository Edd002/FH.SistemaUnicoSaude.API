package com.fiap.hackathon.domain.loadtable;

import com.fiap.hackathon.domain.loadtable.entity.LoadTable;
import com.fiap.hackathon.domain.loadtable.usecase.LoadTableCreateUseCase;
import com.fiap.hackathon.domain.loadtable.usecase.LoadTableEntityLoadEnabledCase;
import com.fiap.hackathon.global.base.BaseServiceGateway;
import com.fiap.hackathon.global.exception.EntityNullException;
import com.fiap.hackathon.global.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadTableServiceGateway extends BaseServiceGateway<ILoadTableRepository, LoadTable> {

    private final ILoadTableRepository loadTableRepository;

    @Autowired
    public LoadTableServiceGateway(ILoadTableRepository loadTableRepository) {
        this.loadTableRepository = loadTableRepository;
    }

    public boolean isEntityLoadEnabled(String entityName) {
        return new LoadTableEntityLoadEnabledCase(loadTableRepository.findByEntityName(entityName)).isEntityLoadEnabled();
    }

    public void create(String entityName) {
        if (ValidationUtil.isBlank(entityName)) {
            throw new EntityNullException("Nenhuma entidade foi informada para ser cadastrada ou atualizada.");
        }
        save(new LoadTableCreateUseCase(loadTableRepository.findByEntityName(entityName), entityName).getBuiltedLoadTable());
    }

    @Override
    public LoadTable findByHashId(String hashId) {
        return super.findByHashId(hashId, String.format("A carregamento de tabela com o hash id %s não foi encontrado.", hashId));
    }
}