package com.fiap.hackathon.domain.loadtable;

import com.fiap.hackathon.domain.loadtable.entity.LoadTable;
import com.fiap.hackathon.global.base.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoadTableRepository extends IBaseRepository<LoadTable> {

    LoadTable findByEntityName(String entityName);
}