package com.isa.aem.dao;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class DaoTemplate<T> {

    @PersistenceContext
    private EntityManager entityManager;

    Object T;

    public Long save(T t) {
        entityManager.persist(t);
        return null;
    }

    public T update(T t) {
        return entityManager.merge(t);
    }

    public T findById(Long id) {
        return (T) entityManager.find(T.getClass(), id);
    }

    public void delete(Long id) {
        T tId = findById(id);
        if (tId != null) {
            entityManager.remove(tId);
        }
    }

    public List<T> findAll() {
//        final Query query = entityManager.createQuery("SELECT s FROM T s");
////        return query.getResultList();
        return null;
    }
}
