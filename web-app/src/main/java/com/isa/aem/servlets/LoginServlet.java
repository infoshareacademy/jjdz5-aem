package com.isa.aem.servlets;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.isa.aem.dao.GenericDao;
import com.isa.aem.informationcollect.RecordCreator;
import com.isa.aem.model.User;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static final String ID_TOKEN_PARAMETER = "id_token";
    private static final String NAME_PARAMETER = "name";
    private static final String USER_NAME_PARAMETER = "userName";

    @Inject
    private RecordCreator recordCreator;
    @Inject
    private GenericDao<User> userDao;

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");

        try {
            String idToken = req.getParameter(ID_TOKEN_PARAMETER);
            GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
            String name = (String) payLoad.get(NAME_PARAMETER);
            String email = payLoad.getEmail();
            System.out.println("User name: " + name);
            System.out.println("User email: " + email);

            HttpSession session = req.getSession(true);
            session.setAttribute(USER_NAME_PARAMETER, name);
            addUser(req);
            resp.sendRedirect("/currency-manager");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void addUser(HttpServletRequest req) {
        String nameByGoogle = recordCreator.getNameByGoogle(req);
        String emailByGoogle = recordCreator.getEmailByGoogle(req);
        LocalDateTime loginDateTimeFromSession = recordCreator.getLoginDateTimeFromSession(req);

        Boolean isAdmin = recordCreator.isAdmin(emailByGoogle);

        User user = recordCreator.createUser(
                nameByGoogle,
                emailByGoogle,
                loginDateTimeFromSession,
                isAdmin);

        userDao.save(user);
    }
}
