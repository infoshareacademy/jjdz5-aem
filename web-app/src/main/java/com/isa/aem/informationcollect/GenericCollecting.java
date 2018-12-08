package com.isa.aem.informationcollect;

import com.isa.aem.dao.GenericDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
@Any
public class GenericCollecting<T> {
    public Class<T> L;
    Logger LOG = LoggerFactory.getLogger(L);

    @Inject
    GenericDao<T> genericDao;

    private static final String ACTION = "action";
    private static final String ADD_OBJECT = "add";
    private static final String DELETE_OBJECT = "delete";
    private static final String FIND_ALL_OBJECTS = "findAll";
    private static final String UPDATE_OBJECT = "update";
    private static final String UNKNOWN_ACTION = "Unknown action";
    private static final String EMPTY_ACTION_PARAMETER = "Empty action parameter.";

//    public String parameterAction(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String action = req.getParameter(ACTION);
//        LOG.info("Requested action: {}", action);
//        checkActionIfNullOrEmpty(resp);
//        return action;
//    }
//
//    public void add(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        findAll(req, resp);
//    }
//
//    public void delete(HttpServletRequest req, HttpServletResponse resp)
//            throws Exception {
//        findAll(req, resp);
//    }
//
//    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        findAll(req, resp);
//    }
//
//    public void findAll(HttpServletRequest req, HttpServletResponse resp)
//            throws IOException {
//        final List<T> result = genericDao.findAll();
//        LOG.info("Found {} objects", result.size());
//        for (T t : result) {
//            resp.getWriter().write(result.toString() + "\n");
//        }
//    }
}
