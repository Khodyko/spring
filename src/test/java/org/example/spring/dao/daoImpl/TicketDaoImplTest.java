package org.example.spring.dao.daoImpl;

import junit.framework.TestCase;
import org.example.spring.Storage;
import org.example.spring.dao.ExceptionDao.DaoException;
import org.example.spring.model.Entity.EventEntity;
import org.example.spring.model.Entity.TicketEntity;
import org.example.spring.model.Entity.UserEntity;
import org.example.spring.model.Ticket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicketDaoImplTest extends TestCase {
    @Mock
    private Storage storage;
    @Mock
    private ValidatorDao validatorDao;
    @InjectMocks
    private TicketDaoImpl ticketDaoImpl;

    @Test
    public void testSaveBookedTicket() {
        TicketEntity ticketEntity = new TicketEntity(12, 12, Ticket.Category.BAR, 12);
        Map<String, TicketEntity> ticketEntityMap = new HashMap<>();
        ticketEntityMap.put("ticket:" + ticketEntity.getId(), ticketEntity);
        when(storage.getTicketMap()).thenReturn(ticketEntityMap);
        TicketEntity ticketEntityFromMock = (TicketEntity) ticketDaoImpl.saveBookedTicket(12, 12, 12, Ticket.Category.BAR);
        assertNotNull(ticketEntityFromMock);
    }

    @Test
    public void testGetBookedTicketsByUser() {
        List<Ticket> ticketList = new ArrayList<>();
        TicketEntity ticketEntity = new TicketEntity(12, 12, Ticket.Category.BAR, 12);
        UserEntity userEntity = new UserEntity(12, "sergei", "sergei@mail.ru");
        Map<String, TicketEntity> ticketEntityMap = new HashMap<>();
        ticketEntityMap.put("ticket:" + ticketEntity.getId(), ticketEntity);
        when(storage.getTicketMap()).thenReturn(ticketEntityMap);
        try {
            when(validatorDao.validateListForPage(any(Integer.class), any(Integer.class))).thenReturn(true);
        } catch (DaoException e) {
            //fix me
            e.printStackTrace();
        }
        try {
            ticketList = ticketDaoImpl.getBookedTickets(userEntity, 100, 0);
        } catch (DaoException e) {
            //fix me
            e.printStackTrace();
        }
        assertEquals(userEntity.getId(), ticketList.get(0).getUserId());
    }

    @Test
    public void testGetBookedTicketsByEvent() {
        Date day = new Date(System.currentTimeMillis());
        List<Ticket> ticketList = new ArrayList<>();
        TicketEntity ticketEntity = new TicketEntity(12, 12, Ticket.Category.BAR, 12);
        EventEntity eventEntity = new EventEntity(12, "title12", day);
        Map<String, TicketEntity> ticketEntityMap = new HashMap<>();
        ticketEntityMap.put("ticket:" + ticketEntity.getId(), ticketEntity);
        when(storage.getTicketMap()).thenReturn(ticketEntityMap);
        try {
            when(validatorDao.validateListForPage(any(Integer.class), any(Integer.class))).thenReturn(true);
        } catch (DaoException e) {
            //fix me
            e.printStackTrace();
        }
        try {
            ticketList = ticketDaoImpl.getBookedTickets(eventEntity, 100, 0);
        } catch (DaoException e) {
            //fix me
            e.printStackTrace();
        }
        assertEquals(eventEntity.getId(), ticketList.get(0).getEventId());
    }

    @Test
    public void testCancelTicket() {
        TicketEntity ticketEntity = new TicketEntity(12, 12, 12, Ticket.Category.BAR, 12);
        Map<String, TicketEntity> ticketEntityMap = new HashMap<>();
        ticketEntityMap.put("ticket:" + ticketEntity.getId(), ticketEntity);
        when(storage.getTicketMap()).thenReturn(ticketEntityMap);
        Boolean isTicketCanceled = ticketDaoImpl.cancelTicket(12);
        assertTrue(isTicketCanceled);
    }

    @Test
    public void testGetTicketById() {
        TicketEntity ticketEntity = new TicketEntity(12, 12, 12, Ticket.Category.BAR, 12);
        Map<String, TicketEntity> ticketEntityMap = new HashMap<>();
        ticketEntityMap.put("ticket:" + ticketEntity.getId(), ticketEntity);
        when(storage.getTicketMap()).thenReturn(ticketEntityMap);
        TicketEntity ticketEntityFromMock = (TicketEntity) ticketDaoImpl.getTicketById(12);
        assertEquals(ticketEntity, ticketEntityFromMock);
    }
}