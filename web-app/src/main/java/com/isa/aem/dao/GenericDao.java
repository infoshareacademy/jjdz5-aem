package com.isa.aem.dao;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Default
@Any
public class GenericDao<T> {

    public Class<T> C;

    @PersistenceContext
    private EntityManager entityManager;

    public Long save(T t) {
        entityManager.persist(t);
        return null;
    }

    public T update(T t) {
        return entityManager.merge(t);
    }

    public T findById(Long id) {
        return entityManager.find(C, id);
    }

    public void delete(Long id) {
        T tId = findById(id);
        if (tId != null) {
            entityManager.remove(tId);
        }
    }

    public List<T> findAll() {
        final Query query = entityManager.createQuery("SELECT s FROM" + C + " s");
        return query.getResultList();
    }
}
