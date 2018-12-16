package com.isa.aem.dao;

import com.isa.aem.model.Activity;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ActivityDao extends GenericDao<Activity> {

    public List<Activity> findAll() {
        final Query query = entityManager.createQuery("SELECT a FROM Activity a");
        return query.getResultList();
    }

    public Activity findById(Long id) {
        return entityManager.find(Activity.class, id);
    }

    public void delete(Long id) {
        Activity byId = findById(id);
        if (byId != null) {
            entityManager.remove(byId);
        }
    }

    public List<Activity> findAllTodaysActivitiesByMembers() {
        @SuppressWarnings("JpaQlInspection") final Query query = entityManager.createQuery("" +
                "SELECT distinct b FROM Activity b " +
                "WHERE b.user IS NOT NULL " +
                "AND b.actionDate >= current_date " +
                "ORDER BY b.actionDate DESC");

        return query.getResultList();
    }

    public List<Activity> findAllTodaysActivitiesByGuests() {
        @SuppressWarnings("JpaQlInspection") final Query query = entityManager.createQuery("" +
                "SELECT b FROM Activity b " +
                "WHERE b.user IS NULL " +
                "AND b.actionDate >= current_date " +
                "ORDER BY b.actionDate DESC");

        return query.getResultList();
    }
}
