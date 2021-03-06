package com.isa.aem.dao;

import com.isa.aem.model.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserDao extends GenericDao<User> {

    public boolean checkIfEmailExist(String email) {
        final Query query = entityManager.createQuery("SELECT u.email FROM User u WHERE u.email = :param");
        query.setParameter("param", email);
        return query.getResultList().isEmpty();
    }

    public List<User> findAll() {
        final Query query = entityManager.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }

    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    public void delete(Long id) {
        User byId = findById(id);
        if (byId != null) {
            entityManager.remove(byId);
        }
    }

    public Long save(User user) {
        entityManager.persist(user);
        return user.getId();
    }
    public User update(User t) {
        return entityManager.merge(t);
    }
}
