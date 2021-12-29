package org.example.spring.dao.daoImpl;

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

public class TicketDaoImpl implements TicketDao {
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

    public TicketDaoImpl() {
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {

        return null;
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) throws DaoException {
        List<Ticket> ticketList=new ArrayList<>();
        Map<String, TicketEntity> ticketEntityMap=storage.getTicketMap();
        for(Map.Entry<String, TicketEntity> entry: ticketEntityMap.entrySet()){
            if(entry.getValue().getUserId()==user.getId()){
                ticketList.add(entry.getValue());
            }
            if(validatorDao.validateListForPage(ticketList,pageSize,pageNum)){
                return getPagedList(ticketList,pageSize,pageNum);
            }
        }

        return null;
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return false;
    }

    private List<Ticket> getPagedList(List<Ticket> ticketList, Integer pageSize, Integer pageNum) {
        List<Ticket> pagedList = new ArrayList<>();
        pagedList = (List<Ticket>) ticketList.stream().
                skip(pageSize * pageNum).limit(pageNum).
                collect(Collectors.toList());
        return pagedList;
    }

}
