package org.example.spring.service;

import org.example.spring.facade.BookingFacade;
import org.example.spring.facade.FacadeImpl;
import org.example.spring.model.Event;
import org.example.spring.model.Ticket;
import org.example.spring.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {


    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category);

    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);

    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);

    public boolean cancelTicket(long ticketId);
}
