package com.isa.aem.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.aem.dao.ActivityDao;
import com.isa.aem.model.Activity;
import com.isa.aem.service.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

public class JsonConverter {

    @Inject
    private ActivityDao activityDao;

    private Logger LOG = LoggerFactory.getLogger(ActivityService.class);

    public ActivityToReport convertActivities(List<Activity> list) {
        ObjectMapper mapper = new ObjectMapper();

        ActivityToReport activityToReport = null;
        try {
            activityToReport = mapper.readValue((JsonParser) list, ActivityToReport.class);
            LOG.info(activityToReport.getId().toString());
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        return activityToReport;
    }
}
