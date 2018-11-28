package com.isa.aem.servlets;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
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

        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        resp.setContentType("text/html;charset=UTF-8 pageEncoding=\"UTF-8");

        try {
            HttpSession session = req.getSession();
            session.invalidate();
            eraseCookie(req, resp);


            LOG.info("Wylogowanie ze strony. Powrót do strony głównej.");
            resp.sendRedirect("/calculator");
//            req.getServletContext()
//                    .getRequestDispatcher("mainMenu").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void eraseCookie(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
    }

    private void removeCookies(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        for (Cookie c : cookies) {
            c.setMaxAge(0);
            resp.addCookie(c);
        }
    }

}
