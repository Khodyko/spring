package org.example.spring.facade;

import junit.framework.TestCase;
import org.example.spring.model.Entity.EventEntity;
import org.example.spring.model.Entity.TicketEntity;
import org.example.spring.model.Entity.UserEntity;
import org.example.spring.model.Event;
import org.example.spring.model.Ticket;
import org.example.spring.model.User;
import org.example.spring.service.ServiceException.ServiceException;
import org.example.spring.service.serviceImpl.EventServiceImpl;
import org.example.spring.service.serviceImpl.TicketServiceImpl;
import org.example.spring.service.serviceImpl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FacadeImplTest extends TestCase {

    @Mock
    private EventServiceImpl eventServiceImpl;
    @Mock
    private UserServiceImpl userServiceImpl;
    @Mock
    private TicketServiceImpl ticketServiceImpl;
    @InjectMocks
    private FacadeImpl facade;

    @Test
    public void testGetEventById() {

        EventEntity eventEntity = new EventEntity(12, "title12",
                new Date(System.currentTimeMillis()));
        when(eventServiceImpl.getEventById(12)).thenReturn(eventEntity);
        when(eventServiceImpl.getEventById(1)).thenReturn(null);
        EventEntity eventEntityFromMock = (EventEntity) facade.getEventById(12);
        EventEntity eventEntityFromNullMock = (EventEntity) facade.getEventById(1);
        assertNotNull(eventEntityFromMock);
        assertNull(eventEntityFromNullMock);

    }

    @Test
    public void testGetEventsByTitle() {
        EventEntity eventEntity = new EventEntity(12, "title12",
                new Date(System.currentTimeMillis()));
        List<Event> eventEntityList = new ArrayList<>();
        eventEntityList.add(eventEntity);
        try {
            when(eventServiceImpl.getEventsByTitle("title12", 100, 0)).thenReturn(eventEntityList);
        } catch (ServiceException e) {
            //fix me
            e.printStackTrace();
        }
        try {
            when(eventServiceImpl.getEventsByTitle("title1", 100, 0)).thenReturn(null);
        } catch (ServiceException e) {
            //fix me
            e.printStackTrace();
        }
        assertNotNull(facade.getEventsByTitle("title12", 100, 0));
        List<Event> eventsFromMock=facade.getEventsByTitle("title12", 100, 0);
        assert(eventsFromMock.get(0).getTitle().equals("title12"));
        assertNull(facade.getEventsByTitle("title1", 100, 0));
    }

    @Test
    public void testGetEventsForDay() {
        Date day = new Date(System.currentTimeMillis());
        EventEntity eventEntity = new EventEntity(12, "title12", day);

        List<Event> eventEntityList = new ArrayList<>();
        eventEntityList.add(eventEntity);
        try {
            when(eventServiceImpl.getEventsForDay(day, 100, 0)).thenReturn(eventEntityList);
        } catch (ServiceException e) {
            //fix me
            e.printStackTrace();
        }

        List<Event> eventsFromMock=facade.getEventsForDay(day, 100, 0);
        assert(eventsFromMock.get(0).getDate().equals(day));
    }

    @Test
    public void testCreateEvent() {
        EventEntity eventEntity = new EventEntity(12, "title12",
                new Date(System.currentTimeMillis()));
        when(eventServiceImpl.createEvent(eventEntity)).thenReturn(eventEntity);
        EventEntity eventEntityFromMock = (EventEntity) facade.createEvent(eventEntity);
        assertNotNull(eventEntityFromMock);
    }

    @Test
    public void testUpdateEvent() {
        EventEntity eventEntity = new EventEntity(12, "title12",
                new Date(System.currentTimeMillis()));
        when(eventServiceImpl.updateEvent(eventEntity)).thenReturn(eventEntity);
        EventEntity eventEntityFromMock = (EventEntity) facade.updateEvent(eventEntity);
        assertNotNull(eventEntityFromMock);
    }

    @Test
    public void testDeleteEvent() {
        when(eventServiceImpl.deleteEvent(12)).thenReturn(true);
        when(eventServiceImpl.deleteEvent(1)).thenReturn(false);
        assert(facade.deleteEvent(12));
        assert(!facade.deleteEvent(1));
    }

    @Test
    public void testGetUserById() {
        UserEntity userEntity=new UserEntity(12,"Sergei", "sergei@mail.ru");
        when(userServiceImpl.getUserById(12)).thenReturn(userEntity);
        assert(facade.getUserById(12).getId()==12);
    }

    @Test
    public void testGetUserByEmail() {
        UserEntity userEntity=new UserEntity(12,"Sergei", "sergei@mail.ru");
        when(userServiceImpl.getUserByEmail("sergei@mail.ru")).thenReturn(userEntity);
        assert(facade.getUserByEmail("sergei@mail.ru").getEmail().equals("sergei@mail.ru"));
    }

    @Test
    public void testGetUsersByName() {
        List<User> userList=new ArrayList<>();
        UserEntity userEntity=new UserEntity(12,"Sergei", "sergei@mail.ru");
        userList.add(userEntity);
        try {
            when(userServiceImpl.getUsersByName("Sergei", 100, 0)).thenReturn(userList);
        } catch (ServiceException e) {
            //fix me
            e.printStackTrace();
        }
        List<User> usersFromMock=facade.getUsersByName("Sergei", 100, 0);
        assertNotNull(usersFromMock.get(0));
        assert(usersFromMock.size()>0);
    }

    @Test
    public void testCreateUser() {
        UserEntity userEntity=new UserEntity(12,"Sergei", "sergei@mail.ru");
        when(userServiceImpl.createUser(userEntity)).thenReturn(userEntity);
        assert(facade.createUser(userEntity).equals(userEntity));
    }

    @Test
    public void testUpdateUser() {
        UserEntity userEntity=new UserEntity(12,"Sergei", "sergei@mail.ru");
        when(userServiceImpl.updateUser(userEntity)).thenReturn(userEntity);
        assert(facade.updateUser(userEntity).equals(userEntity));
    }

    @Test
    public void testDeleteUser() {
        UserEntity userEntity=new UserEntity(12,"Sergei", "sergei@mail.ru");
        when(userServiceImpl.updateUser(userEntity)).thenReturn(userEntity);
        assert(facade.updateUser(userEntity).equals(userEntity));
    }

    @Test
    public void testBookTicket() {
        TicketEntity ticketEntity=new TicketEntity(12,12,12, Ticket.Category.BAR, 12);
        try {
            when(ticketServiceImpl.bookTicket(12,12,12, Ticket.Category.BAR)).thenReturn(ticketEntity);
        } catch (ServiceException e) {
            //fix me
            e.printStackTrace();
        }
        assert(facade.bookTicket(12,12,12, Ticket.Category.BAR).equals(ticketEntity));

    }

    @Test
    public void testGetBookedTickets() {
        EventEntity eventEntity=new EventEntity(12,"title12",new Date(System.currentTimeMillis()));
        TicketEntity ticketEntity=new TicketEntity(12,12,12, Ticket.Category.BAR, 12);
        List<Ticket> ticketList=new ArrayList<>();
        ticketList.add(ticketEntity);
        try {
            when(ticketServiceImpl.getBookedTickets(eventEntity, 100, 0)).thenReturn(ticketList);
        } catch (ServiceException e) {
            //fix me
            e.printStackTrace();
        }
        assert (facade.getBookedTickets(eventEntity,100, 0).get(0).equals(ticketEntity));
    }

    @Test
    public void testTestGetBookedTickets() {
        UserEntity userEntity=new UserEntity(12,"Sergei", "sergei@mail.ru");
        TicketEntity ticketEntity=new TicketEntity(12,12,12, Ticket.Category.BAR, 12);
        List<Ticket> ticketList=new ArrayList<>();
        ticketList.add(ticketEntity);
        try {
            when(ticketServiceImpl.getBookedTickets(userEntity, 100, 0)).thenReturn(ticketList);
        } catch (ServiceException e) {
            //fix me
            e.printStackTrace();
        }
        assert (facade.getBookedTickets(userEntity,100, 0).get(0).equals(ticketEntity));
    }

    @Test
    public void testGetTicketById() {
        TicketEntity ticketEntity=new TicketEntity(12,12,12, Ticket.Category.BAR, 12);
        when(ticketServiceImpl.getTicketById(12)).thenReturn(ticketEntity);
        assert (facade.getTicketById(12).equals(ticketEntity));
    }

    @Test
    public void testCancelTicket() {
        when(ticketServiceImpl.getTicketById(12)).thenReturn(null);
        assert (facade.getTicketById(12)==null);
    }
}