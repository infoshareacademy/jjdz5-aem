package com.isa.aem.servlets;

import com.isa.aem.dao.UserDao;
import com.isa.aem.informationcollect.RecordCreator;
import com.isa.aem.model.Activity;
import com.isa.aem.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    private Logger LOG = LoggerFactory.getLogger(LogoutServlet.class);

    @Inject
    RecordCreator recordCreator;

    @Inject
    UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            session.invalidate();

            addLogoutDateTime(req);
            LOG.info("Logout, return to main site.");
            resp.sendRedirect("/calculator");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void addLogoutDateTime(HttpServletRequest req) {

        Long id = recordCreator.findIdFromDataBaseByEmail(req);

        User user = userDao.findById(id);

        Activity logoutDataTime = recordCreator.logOutTime(LocalDateTime.now());

        List<Activity> activities = user.getActivities();
        activities.add(logoutDataTime);
    }
}
