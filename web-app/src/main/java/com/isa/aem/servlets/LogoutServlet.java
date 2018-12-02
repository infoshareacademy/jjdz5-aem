package com.isa.aem.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    private Logger LOG = LoggerFactory.getLogger(LogoutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            session.invalidate();


            LOG.info("Wylogowanie ze strony. Powrót do strony głównej.");
            resp.sendRedirect("/calculator");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
