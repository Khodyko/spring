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
                break;
            }
        }

        return getPagedList(eventList, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) throws DaoException {
        List<Event> eventList = new ArrayList<>();
        Map<String, EventEntity> eventEntityMap = storage.getEventMap();
        for (Map.Entry<String, EventEntity> entry : eventEntityMap.entrySet()) {
            if (entry.getValue().getDate().equals(day)) {
                eventList.add(entry.getValue());
                break;
            }
        }
        return getPagedList(eventList, pageSize, pageNum);
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
    @Override
    public <T> List<T> getPagedList(List<T> list, int pageSize, int pageNum) throws DaoException {
        List<T> pagedList;
        Integer sizeFullList = list.size();
        try {
            if (pageSize < 0 || pageNum < 0) {
                throw new IllegalArgumentException("page must be positive number");
            } else if (pageSize <= sizeFullList || sizeFullList == 0 || list == null) {
                return list;
            } else if (sizeFullList % pageSize < pageNum) {
                throw new IllegalArgumentException("We have only " + sizeFullList % pageSize +
                        " pages and page № " + pageNum + " is not exist");
            } else {
                pagedList = new ArrayList<>();
                pagedList = (List<T>) list.stream().skip(pageSize * pageNum).limit(pageNum).collect(Collectors.toList());
                return pagedList;
            }
        } catch (IllegalArgumentException e) {
            throw new DaoException(e.getMessage(), e);
        }

    }
}
