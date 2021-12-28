package org.example.spring.service.serviceImpl;

import org.example.spring.dao.daoImpl.EventDaoImpl;
import org.example.spring.model.Event;
import org.example.spring.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


public class EventServiceImpl implements EventService {
    private EventDaoImpl eventDaoImpl;

@Autowired
    public EventServiceImpl(EventDaoImpl eventDaoImpl) {
        this.eventDaoImpl = eventDaoImpl;
    }

    @Override
    public Event getEventById(long eventId) {

        return eventDaoImpl.getEventById(eventId);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventDaoImpl.getEventsByTitle(title,pageSize,pageNum);
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return eventDaoImpl.getEventsForDay(day, pageSize, pageNum);
    }

    @Override
    public Event createEvent(Event event) {
        return eventDaoImpl.createEvent(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventDaoImpl.updateEvent(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return eventDaoImpl.deleteEvent(eventId);
    }
}
