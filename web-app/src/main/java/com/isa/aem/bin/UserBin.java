package com.isa.aem.bin;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.isa.aem.dao.UserDao;
import com.isa.aem.model.User;
import com.isa.aem.servlets.IdTokenVerifierAndParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserBin extends DataBaseTemplate<User, UserBin> {

    Logger LOG = LoggerFactory.getLogger(UserBin.class);

    @Inject
    UserDao userDao;

    private static final String ID_TOKEN_PARAMETER = "id_token";
    private static final String NAME_PARAMETER = "name";
    private static final String ADMIN_EMAIL = "currencymanager2018@gmail.com";

    @Override
    public String parameterAction(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return super.parameterAction(req, resp);
    }

    @Override
    public void databaseSupport(String request, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        super.databaseSupport(request, req, resp);
    }

    @Override
    public void add(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String name = getName(req);
        String email = getEmail(req);
        Boolean isAdmin = isAdmin(ADMIN_EMAIL, name);

        final User user = new User(name, email, isAdmin);
        userDao.save(user);

        super.add(req, resp);
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long id = getUserIdByParameter(req);
        String name = getName(req);
        LOG.info("Removing user: {}, with id: {}", name, id);
        userDao.delete(id);
        super.delete(req, resp);
    }

    @Override
    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = getUserIdByParameter(req);
        User user = userDao.findById(id);

        user.s
        super.update(req, resp);
    }

    @Override
    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        super.findAll(req, resp);
    }

    @Override
    public Long getUserIdByParameter(HttpServletRequest req) {
        return super.getUserIdByParameter(req);
    }

    private Boolean isAdmin(String admin, String parameter) {
        return admin.equals(parameter);
    }

    public GoogleIdToken.Payload googleSupport(HttpServletRequest req) throws Exception {
        String idToken = req.getParameter(ID_TOKEN_PARAMETER);
        GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
        return payLoad;
    }

    private String getName(HttpServletRequest req) throws Exception {
        GoogleIdToken.Payload payLoad = googleSupport(req);
        return (String) payLoad.get(NAME_PARAMETER);
    }

    private String getEmail(HttpServletRequest req) throws Exception {
        GoogleIdToken.Payload payLoad = googleSupport(req);
        return payLoad.getEmail();
    }


}
