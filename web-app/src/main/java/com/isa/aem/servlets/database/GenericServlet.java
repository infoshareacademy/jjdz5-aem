package com.isa.aem.servlets.database;

import com.isa.aem.dao.GenericDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GenericServlet<T> extends HttpServlet {
    public Class<T> E;

    Logger LOG = LoggerFactory.getLogger(E.getClass());

    @Inject
    GenericDao<T> daoTemplate;

    private static final String ACTION = "action";
    private static final String ADD_OBJECT = "add";
    private static final String DELETE_OBJECT = "delete";
    private static final String FIND_ALL_OBJECTS = "findAll";
    private static final String UPDATE_OBJECT = "update";
    private static final String UNKNOWN_ACTION = "Unknown action";
    private static final String EMPTY_ACTION_PARAMETER = "Empty action parameter.";

    public String parameterAction(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter(ACTION);
        LOG.info("Requested action: {}", action);
        checkActionIfNullOrEmpty(resp);
        return action;
    }

    public void databaseSupport(String request, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (addToDatabase(request)) {
            add(req, resp);
        } else if (deleteFromDatabase(request)) {
            delete(req, resp);
        } else if (findAllObjects(request)) {
            findAll(req, resp);
        } else if (updateDataBase(request)) {
            update(req, resp);
        } else {
            resp.getWriter().write(UNKNOWN_ACTION);
        }
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        findAll(req, resp);
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        findAll(req, resp);
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        findAll(req, resp);
    }

    public void findAll(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        final List<T> result = daoTemplate.findAll();
        LOG.info("Found {} objects", result.size());
        for (T t : result) {
            resp.getWriter().write(result.toString() + "\n");
        }
    }

    public Long getIdByParameter(HttpServletRequest req) {
        return Long.valueOf(req.getParameter("id"));
    }

    private void checkActionIfNullOrEmpty(HttpServletResponse resp) throws IOException {
        if (actionIsNull() || actionIsEmpty()) {
            resp.getWriter().write(EMPTY_ACTION_PARAMETER);
            return;
        }
    }

    private boolean actionIsNull() {
        return ACTION == null;
    }

    private boolean actionIsEmpty() {
        return ACTION.isEmpty();
    }

    private boolean addToDatabase(String request) {
        return request.equals(ADD_OBJECT);
    }

    private boolean deleteFromDatabase(String request) {
        return request.equals(DELETE_OBJECT);
    }

    private boolean findAllObjects(String requset) {
        return requset.equals(FIND_ALL_OBJECTS);
    }

    private boolean updateDataBase(String request) {
        return request.equals(UPDATE_OBJECT);
    }
}
