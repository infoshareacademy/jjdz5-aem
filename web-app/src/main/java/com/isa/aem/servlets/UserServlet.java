package com.isa.aem.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    Logger LOG = LoggerFactory.getLogger(UserServlet.class);


}
