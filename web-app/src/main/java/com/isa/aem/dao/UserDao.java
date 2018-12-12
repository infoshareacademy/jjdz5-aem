package com.isa.aem.dao;

import com.isa.aem.model.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserDao extends GenericDao<User> {

    public List<String> findIdByEmail(String email) {
        final Query query = entityManager.createNativeQuery("SELECT ID FROM USERS WHERE EMAIL =" + email);
        return query.getResultList();
    }

    public List<String> findEmailInDatabase(String email) {
        final Query query = entityManager.createQuery("SELECT s FROM User s WHERE s.email = :param");
        query.setParameter("param", email);
        return query.getResultList();
    }
}
