package com.isa.aem.service;

import com.isa.aem.dao.UserDao;
import com.isa.aem.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class UserService {

    private Logger LOG = LoggerFactory.getLogger(ActivityService.class);

    @Inject
    private UserDao userDao;

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLoggedInUsers() {
        final List<User> users = userDao.findAll();
        LOG.info("Found " + users.size() + " users");

        if (!users.isEmpty()) {
            return Response.ok(users).build();
        }
        return Response.noContent().build();
    };
}
