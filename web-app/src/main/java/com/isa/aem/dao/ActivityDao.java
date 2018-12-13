package com.isa.aem.dao;

import com.isa.aem.model.Activity;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ActivityDao extends GenericDao<Activity> {

    public List<Activity> findAllTodaysActivitiesByLoggedInUsers() {
        final Query query = entityManager.createQuery("SELECT s FROM Activity s WHERE s.user IS NOT null");

        return query.getResultList();
    }
}

