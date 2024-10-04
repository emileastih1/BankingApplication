package com.sg.finance.core.application.ports.output;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface CommonBaseRepository<T, ID> extends Repository<T, ID> {
    T getById(ID id);

    T save(T entity);
}
