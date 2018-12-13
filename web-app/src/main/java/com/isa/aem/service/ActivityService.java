package com.isa.aem.service;

import com.isa.aem.dao.ActivityDao;
import com.isa.aem.model.Activity;
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
public class ActivityService {

    private Logger LOG = LoggerFactory.getLogger(ActivityService.class);

    @Inject
    private ActivityDao activityDao;

    @GET
    @Path("/activities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTodaysActivitiesByLoggedInUsers() {
        final List<Activity> activities = activityDao.findAllTodaysActivitiesByLoggedInUsers();
        LOG.info("Found " + activities.size() + " activities by logged in users");

        if (!activities.isEmpty()) {
            return Response.ok(activities).build();
        }
        return Response.noContent().build();
    };
}
