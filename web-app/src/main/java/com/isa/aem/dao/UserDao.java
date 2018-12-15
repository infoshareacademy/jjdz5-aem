package com.isa.aem.dao;

import com.isa.aem.model.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserDao extends GenericDao<User> {

    public List<User> findIdByEmail() {
        final Query query = entityManager.createNativeQuery("SELECT * FROM USERS");
        return query.getResultList();
    }

    public List<String> findEmailInDatabase(String email) {
        final Query query = entityManager.createQuery("SELECT u.email FROM User u WHERE u.email = :param");
        query.setParameter("param", email);
        return query.getResultList();
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
}
