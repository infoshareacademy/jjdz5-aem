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

    private Logger LOG = LoggerFactory.getLogger(ActivityService.class);

    public List<Activity> convertJsonToObjectActivity(List<Activity> list) {
        ObjectMapper mapper = new ObjectMapper();

        List<Activity> convertedList = null;

        try {
            convertedList = mapper.readValue((JsonParser) list, mapper.getTypeFactory().constructCollectionType(List.class, Activity.class));
            LOG.info(convertedList.get(0).getId().toString());
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        return convertedList;
    }
}
