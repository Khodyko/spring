package org.example.spring.dao.daoImpl;

import org.example.spring.Storage;
import org.example.spring.dao.EventDao;
import org.example.spring.model.Entity.EventEntity;
import org.example.spring.model.Event;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventDaoImpl implements EventDao {

    private Storage storage;

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Event getEventById(long eventId) {
        EventEntity event=null;
        Map<String, EventEntity> eventEntityMap=storage.getEventMap();
        for(Map.Entry<String, EventEntity> entry: eventEntityMap.entrySet()){
            System.out.println("idvalue= "+entry.getValue().getId());
            if (entry.getValue().getId()==eventId){
                event=entry.getValue();
                break;
            }
        }
        return event;
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public Event createEvent(Event event) {
        return null;
    }

    @Override
    public Event updateEvent(Event event) {
        return null;
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return false;
    }
}
