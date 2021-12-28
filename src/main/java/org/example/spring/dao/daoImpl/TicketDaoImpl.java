package org.example.spring.dao.daoImpl;

import org.example.spring.Storage;
import org.example.spring.dao.TicketDao;
import org.example.spring.model.Event;
import org.example.spring.model.Ticket;
import org.example.spring.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public class TicketDaoImpl implements TicketDao {
    private Storage storage;

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
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
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
}
