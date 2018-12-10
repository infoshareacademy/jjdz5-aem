package com.isa.aem.dao;

import com.isa.aem.model.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class UserDao extends GenericDao<User> {

    public List<String> findEmail() {
        // List<Object[]>
        // ( [imie, nazwisko], [imie, nazwisko] )
//        final Query query = entityManager.createNativeQuery("SELECT NAME, SURNAME FROM STUDENTS");
//        List<Object[]> result = query.getResultList();
//        return result.stream().map(obj -> obj[0] + " " + obj[1]).collect(Collectors.toList());

        final Query query = entityManager.createNativeQuery("SELECT concat(ID, \" \", EMAIL) FROM USERS WHERE ID = MAX;");
        return (List<String>) query.getResultList();
    }

    public List<LocalDate> findDates() {
        // SELECT date_of_birth FROM STUDENTS ORDER BY date_of_birth DESC;
        final Query query = entityManager.createQuery("SELECT s.dateOfBirth FROM Student s ORDER BY s.dateOfBirth DESC");
        return query.getResultList();
    }

    public List<Student> findBornAfter(LocalDate date) {
        Query query = entityManager.createQuery("SELECT s FROM Student s WHERE s.dateOfBirth > :param ORDER BY s.dateOfBirth DESC");
        query.setParameter("param", date);
        return query.getResultList();
    }
}
