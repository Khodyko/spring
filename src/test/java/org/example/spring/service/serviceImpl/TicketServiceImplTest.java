package org.example.spring.service.serviceImpl;

import junit.framework.TestCase;
import org.example.spring.dao.ExceptionDao.DaoException;
import org.example.spring.dao.TicketDao;
import org.example.spring.dao.daoImpl.TicketDaoImpl;
import org.example.spring.model.Entity.EventEntity;
import org.example.spring.model.Entity.TicketEntity;
import org.example.spring.model.Entity.UserEntity;
import org.example.spring.model.Ticket;
import org.example.spring.service.ServiceException.ServiceException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest extends TestCase {
    @Mock
    private TicketDaoImpl ticketDaoImpl;
    @InjectMocks
    private TicketServiceImpl ticketServiceImpl;

    public void testBookTicket() {
        TicketEntity ticketEntity = new TicketEntity(12, 12, 12, Ticket.Category.BAR, 12);

        when(ticketDaoImpl.saveBookedTicket(12, 12, 12, Ticket.Category.BAR)).thenReturn(ticketEntity);
        try {
            assert (ticketServiceImpl.bookTicket(12, 12, 12, Ticket.Category.BAR).equals(ticketEntity));
        }
        catch (ServiceException e){
            //fix me
            e.printStackTrace();
        }
    }

    public void testGetBookedTickets() {
        EventEntity eventEntity=new EventEntity(12,"title12",new Date(System.currentTimeMillis()));
        TicketEntity ticketEntity=new TicketEntity(12,12,12, Ticket.Category.BAR, 12);
        List<Ticket> ticketList=new ArrayList<>();
        ticketList.add(ticketEntity);
        try {
            when(ticketDaoImpl.getBookedTickets(eventEntity, 100, 0)).thenReturn(ticketList);
        } catch (DaoException e) {
            //fix me
            e.printStackTrace();
        }
        try {
            assert (ticketServiceImpl.getBookedTickets(eventEntity, 100, 0).get(0).equals(ticketEntity));
        } catch (ServiceException e) {
            //fix me
            e.printStackTrace();
        }
    }

    public void testTestGetBookedTickets() {
        UserEntity userEntity=new UserEntity(12,"Sergei", "sergei@mail.ru");
        TicketEntity ticketEntity=new TicketEntity(12,12,12, Ticket.Category.BAR, 12);
        List<Ticket> ticketList=new ArrayList<>();
        ticketList.add(ticketEntity);
        try {
            when(ticketDaoImpl.getBookedTickets(userEntity, 100, 0)).thenReturn(ticketList);
        } catch (DaoException e) {
            //fix me
            e.printStackTrace();
        }
        try {
            assert (ticketServiceImpl.getBookedTickets(userEntity, 100, 0).get(0).equals(ticketEntity));
        } catch (ServiceException e) {
            //fix me
            e.printStackTrace();
        }
    }

    public void testCancelTicket() {
        when(ticketDaoImpl.getTicketById(12)).thenReturn(null);
        assert (ticketServiceImpl.getTicketById(12)==null);
    }

    public void testGetTicketById() {
        TicketEntity ticketEntity=new TicketEntity(12,12,12, Ticket.Category.BAR, 12);
        when(ticketDaoImpl.getTicketById(12)).thenReturn(ticketEntity);
        assert (ticketServiceImpl.getTicketById(12).equals(ticketEntity));
    }
}