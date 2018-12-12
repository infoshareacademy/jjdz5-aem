package com.isa.aem.dao;

import com.isa.aem.model.Activity;
import org.hibernate.sql.Select;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ActivityDao extends GenericDao<Activity> {

    public List<Activity> findAllLoggedInUsers() {
        final Query query = entityManager.createQuery("SELECT s FROM Activity s WHERE s.user IS NOT null");

        return query.getResultList();
    }

    public List<Activity> findAllGuestUsers() {
        final Query query = entityManager.createQuery("SELECT s FROM Activity s WHERE s.user IS null");

        return query.getResultList();
    }
}

