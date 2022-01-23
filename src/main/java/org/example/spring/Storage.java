package org.example.spring;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.spring.model.Entity.EventEntity;
import org.example.spring.model.Entity.TicketEntity;
import org.example.spring.model.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static org.apache.logging.log4j.Level.DEBUG;


public class Storage implements Serializable {
    private final static Logger logger = LogManager.getLogger();
    private static final long serialVersionUID = 1L;
    private JsonReader jsonReader;
    private Map<String, TicketEntity> ticketMap = new HashMap();
    private Map<String, UserEntity> userMap = new HashMap();
    private Map<String, EventEntity> eventMap = new HashMap();
    @Value("${event.file.path}")
    private String eventFilePath;
    @Value("${ticket.file.path}")
    private String ticketFilePath;
    @Value("${user.file.path}")
    private String userFilePath;

    public Storage() {
        logger.log(DEBUG, this.getClass().getSimpleName() + " was created");
    }

    public JsonReader getJsonReader() {
        return jsonReader;
    }

    public void setJsonReader(JsonReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    public Map<String, TicketEntity> getTicketMap() {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        return ticketMap;
    }

    public void setTicketMap(Map<String, TicketEntity> ticketMap) {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        this.ticketMap = ticketMap;
    }

    public Map<String, UserEntity> getUserMap() {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        return userMap;
    }

    public void setUserMap(Map<String, UserEntity> userMap) {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        this.userMap = userMap;
    }

    public Map<String, EventEntity> getEventMap() {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        return eventMap;
    }

    public void setEventMap(Map<String, EventEntity> eventMap) {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        this.eventMap = eventMap;
    }


    public void initMethod() {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        eventMap = jsonReader.readFileJson(eventFilePath, EventEntity.class);
        userMap = jsonReader.readFileJson(userFilePath, UserEntity.class);
        ticketMap = jsonReader.readFileJson(ticketFilePath, TicketEntity.class);
    }


}
