package com.isa.aem.dao;

import com.isa.aem.model.User;

import javax.ejb.Stateless;
import java.util.List;
@Stateless
public class UserDao extends DaoTemplate<User> {

    @Override
    public Long save(User user) {
        return super.save(user);
    }

    @Override
    public User update(User user) {
        return super.update(user);
    }

    @Override
    public User findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public List<User> findAll() {
        return super.findAll();
    }
}
