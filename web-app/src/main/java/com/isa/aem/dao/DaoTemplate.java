package com.isa.aem.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DaoTemplate<T> {

    @PersistenceContext
    private EntityManager entityManager;

    public Long save(T t) {
        entityManager.persist(t);
        return t.
    }
}
