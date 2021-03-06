package org.example.spring.dao.daoImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.spring.Storage;
import org.example.spring.dao.ExceptionDao.DaoException;
import org.example.spring.dao.TicketDao;
import org.example.spring.model.Entity.TicketEntity;
import org.example.spring.model.Event;
import org.example.spring.model.Ticket;
import org.example.spring.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.Level.DEBUG;

public class TicketDaoImpl implements TicketDao {
    private Storage storage;
    private ValidatorDao validatorDao;
    private final static Logger logger = LogManager.getLogger();

    public TicketDaoImpl() {
        logger.log(DEBUG, this.getClass().getSimpleName() + " was created");
    }


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
    public Ticket saveBookedTicket(long userId, long eventId, int place, Ticket.Category category) {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        Map<String, TicketEntity> ticketEntityMap = storage.getTicketMap();
        TicketEntity ticket;
        long ticketId = 0;

        for (Map.Entry<String, TicketEntity> entry : ticketEntityMap.entrySet()) {
            if (entry.getValue().getId() >= ticketId) {
                ticketId = entry.getValue().getId() + 1;
            }
        }
        ticket = new TicketEntity(ticketId, eventId, userId, category, place);
        ticketEntityMap.put("ticket:" + ticketId, ticket);

        return ticket;
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) throws DaoException {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        List<Ticket> ticketList = new ArrayList<>();
        Map<String, TicketEntity> ticketEntityMap = storage.getTicketMap();
        if (validatorDao.validateListForPage(pageSize, pageNum)) {
            for (Map.Entry<String, TicketEntity> entry : ticketEntityMap.entrySet()) {
                if (entry.getValue().getUserId() == user.getId()) {
                    ticketList.add(entry.getValue());
                }

                return getPagedList(ticketList, pageSize, pageNum);
            }
        }
        return null;
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) throws DaoException {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        List<Ticket> ticketList = new ArrayList<>();
        Map<String, TicketEntity> ticketEntityMap = storage.getTicketMap();
        if (validatorDao.validateListForPage(pageSize, pageNum)) {
            for (Map.Entry<String, TicketEntity> entry : ticketEntityMap.entrySet()) {
                if (entry.getValue().getEventId() == event.getId()) {
                    ticketList.add(entry.getValue());
                }

                return getPagedList(ticketList, pageSize, pageNum);
            }
        }
        return null;
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        Map<String, TicketEntity> ticketEntityMap = storage.getTicketMap();
        for (Map.Entry<String, TicketEntity> entry : ticketEntityMap.entrySet()) {
            if (entry.getValue().getId() == ticketId) {
                ticketEntityMap.remove(entry.getKey());
                return true;
            }
        }
        return false;
    }

    @Override
    public Ticket getTicketById(long id) {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        Map<String, TicketEntity> ticketEntityMap = storage.getTicketMap();
        for (Map.Entry<String, TicketEntity> entry : ticketEntityMap.entrySet()) {
            if (entry.getValue().getId() == id) {
                return entry.getValue();
            }
        }
        return null;
    }

    private List<Ticket> getPagedList(List<Ticket> ticketList, Integer pageSize, Integer pageNum) {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        return ticketList.stream().
                skip(pageSize * pageNum).
                limit(pageSize * pageNum + pageSize).
                collect(Collectors.toList());
    }

}
