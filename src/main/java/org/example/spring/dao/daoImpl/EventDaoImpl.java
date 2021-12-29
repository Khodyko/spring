package org.example.spring.dao.daoImpl;

import org.example.spring.Storage;
import org.example.spring.dao.EventDao;
import org.example.spring.dao.ExceptionDao.DaoException;
import org.example.spring.model.Entity.EventEntity;
import org.example.spring.model.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class EventDaoImpl implements EventDao {

    private Storage storage;
    private ValidatorDao validatorDao;

    public ValidatorDao getValidatorDao() {
        return validatorDao;
    }

    public void setValidatorDao(ValidatorDao validatorDao) {
        this.validatorDao = validatorDao;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Event getEventById(long eventId) {
        EventEntity event = null;
        Map<String, EventEntity> eventEntityMap = storage.getEventMap();
        for (Map.Entry<String, EventEntity> entry : eventEntityMap.entrySet()) {
            if (entry.getValue().getId() == eventId) {
                event = entry.getValue();
                break;
            }
        }
        return event;
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) throws DaoException {
        List<Event> eventList = new ArrayList<>();
        Map<String, EventEntity> eventEntityMap = storage.getEventMap();
        for (Map.Entry<String, EventEntity> entry : eventEntityMap.entrySet()) {
            if (entry.getValue().getTitle().equals(title)) {
                eventList.add(entry.getValue());
            }
        }
        if (validatorDao.validateListForPage(eventList, pageSize, pageNum)) {
            return getPagedList(eventList, pageSize, pageNum);
        }
        return null;
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) throws DaoException {
        List<Event> eventList = new ArrayList<>();
        Map<String, EventEntity> eventEntityMap = storage.getEventMap();
        for (Map.Entry<String, EventEntity> entry : eventEntityMap.entrySet()) {
            if (entry.getValue().getDate().equals(day)) {
                eventList.add(entry.getValue());
            }
        }
        if (validatorDao.validateListForPage(eventList, pageSize, pageNum)) {
            return getPagedList(eventList, pageSize, pageNum);
        }
        return null;
    }

    @Override
    public Event createEvent(Event event) {
        Map<String, EventEntity> eventEntityMap = storage.getEventMap();
        eventEntityMap.put("event:" + event.getId(), (EventEntity) event);
        System.out.println("creator: " + eventEntityMap);
        return event;
    }

    @Override
    public Event updateEvent(Event event) {
        Map<String, EventEntity> eventEntityMap = storage.getEventMap();
        if (eventEntityMap.containsKey("event:" + event.getId())) {
            eventEntityMap.put("event:" + event.getId(), (EventEntity) event);
            return event;
        }
        return null;
    }

    @Override
    public boolean deleteEvent(long eventId) {
        Map<String, EventEntity> eventEntityMap = storage.getEventMap();

        return eventEntityMap.remove("event:" + eventId, this.getEventById(eventId));
    }

    private List<Event> getPagedList(List<Event> eventList, Integer pageSize, Integer pageNum) {
        List<Event> pagedList = new ArrayList<>();
        pagedList = (List<Event>) eventList.stream().
                skip(pageSize * pageNum).limit(pageNum).
                collect(Collectors.toList());
        return pagedList;
    }
}
