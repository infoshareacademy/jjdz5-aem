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
}
