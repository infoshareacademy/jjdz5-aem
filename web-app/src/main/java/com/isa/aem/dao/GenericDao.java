package com.isa.aem.dao;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Default
@Any
public class GenericDao<T> {

    public Class<T> C;

    @PersistenceContext
    protected EntityManager entityManager;

    public Long save(T t) {
        entityManager.persist(t);
        return null;
    }

    public T update(T t) {
        return entityManager.merge(t);
    }


}
