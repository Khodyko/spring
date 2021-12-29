package org.example.spring.dao;

import org.example.spring.dao.ExceptionDao.DaoException;
import org.example.spring.model.Event;

import java.util.Date;
import java.util.List;

public interface EventDao extends Pagenable {

    public Event getEventById(long eventId) ;
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) throws DaoException;
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) throws DaoException;
    public Event createEvent(Event event) ;
    public Event updateEvent(Event event) ;
    public boolean deleteEvent(long eventId) ;

}
