package com.isa.aem.servlets.database;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.isa.aem.dao.GenericDao;
import com.isa.aem.model.User;
import com.isa.aem.servlets.IdTokenVerifierAndParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends GenericServlet<User> {

    private static final String ID_TOKEN_PARAMETER = "id_token";
    private static final String NAME_PARAMETER = "name";
    private static final String ADMIN_EMAIL = "currencymanager2018@gmail.com";

    @Inject
    GenericDao<User> userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String request = parameterAction(req, resp);
        try {
            databaseSupport(request, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String name = getName(req);
        String email = getEmail(req);
        Boolean isAdmin = isAdmin(ADMIN_EMAIL, name);

        final User user = new User(name, email, isAdmin);
        daoTemplate.save(user);

        findAll(req, resp);
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long id = getIdByParameter(req);
        userDao.delete(id);

        findAll(req, resp);
    }

    @Override
    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        findAll(req, resp);
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
