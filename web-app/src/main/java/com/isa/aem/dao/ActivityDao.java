package com.isa.aem.dao;

import com.isa.aem.model.Activity;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class ActivityDao extends GenericDao<Activity> {

    @Transactional
    public List<Activity> findAllTodaysActivitiesByLoggedInUsers() {
        @SuppressWarnings("JpaQlInspection")
        final Query query = entityManager.createQuery("" +
                "SELECT a.name, b FROM Activity b " +
                "LEFT JOIN User a " +
                "ON b.user = a.id " +
                "WHERE b.user IS NOT NULL " +
                "AND b.actionDate >= current_date " +
                "ORDER BY b.actionDate DESC");

        return query.getResultList();
    }



}

