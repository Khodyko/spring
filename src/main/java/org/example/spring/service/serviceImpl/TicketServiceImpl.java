package org.example.spring.service.serviceImpl;

import org.example.spring.dao.ExceptionDao.DaoException;
import org.example.spring.dao.daoImpl.TicketDaoImpl;
import org.example.spring.model.Event;
import org.example.spring.model.Ticket;
import org.example.spring.model.User;
import org.example.spring.service.ServiceException.ServiceException;
import org.example.spring.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class TicketServiceImpl implements TicketService {
    private TicketDaoImpl ticketDaoImpl;
    @Autowired
    public TicketServiceImpl(TicketDaoImpl ticketDaoImpl) {

        this.ticketDaoImpl = ticketDaoImpl;
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        return ticketDaoImpl.bookTicket(userId,eventId,place,category);
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) throws ServiceException {
        try {
            return ticketDaoImpl.getBookedTickets(user, pageSize,pageNum);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketDaoImpl.getBookedTickets(event,pageSize,pageNum);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return cancelTicket(ticketId);
    }
}
