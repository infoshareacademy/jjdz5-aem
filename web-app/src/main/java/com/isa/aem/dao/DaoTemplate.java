package com.isa.aem.dao;

import com.sun.org.apache.xml.internal.serialize.LineSeparator;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class DaoTemplate<T> {

    @PersistenceContext
    private EntityManager entityManager;

    public Long save(T t) {
        entityManager.persist(t);
        return null;
    }

    public T update(T t) {
        return entityManager.merge(t);
    }

    public T findById(Long id, Class<T> clazz) {
        return (T) entityManager.find(clazz.getClass(), id);
    }

    public void delete(Long id, Class<T> clazz) {
        T tId = findById(id, clazz);
        if (tId != null) {
            entityManager.remove(tId);
        }
    }

    public List<T> findAll() {
        final Query query = entityManager.createQuery("SELECT s FROM T s");
        return query.getResultList();
    }
}
