package com.isa.aem.servlets.database;

import com.google.api.services.plus.model.Activity;
import com.isa.aem.dao.GenericDao;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActivityServlet extends GenericServlet<Activity> {

    @Inject
    private GenericDao<Activity> activity;

    @Override
    public void add(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Activity activity = new Activity();

        super.add(req, resp);
    }
}
