package com.isa.aem.dao;

import com.isa.aem.model.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserDao extends GenericDao<User> {

    public List findIdByEmail(String email) {
        final Query query = entityManager.createNativeQuery("SELECT ID FROM USERS WHERE EMAIL =" + email);
        return query.getResultList();
    }
}
