package org.example.spring.dao;

import org.example.spring.model.Event;
import org.example.spring.model.Ticket;
import org.example.spring.model.User;

import java.util.List;

public interface TicketDao extends Pagenable {
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category);

    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);

    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);

    public boolean cancelTicket(long ticketId);
}
