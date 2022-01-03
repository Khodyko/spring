package org.example.spring;


import org.example.spring.facade.FacadeImpl;
import org.example.spring.model.Entity.EventEntity;
import org.example.spring.model.Entity.TicketEntity;
import org.example.spring.model.Entity.UserEntity;
import org.example.spring.model.Ticket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


public class UserEventTicketIntegrationTest {
    ApplicationContext ctx= new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void testAddUserAddEventBookTicketCancelTicket(){

        FacadeImpl facade=(FacadeImpl) ctx.getBean("facadeImpl");
        EventEntity eventEntity=new EventEntity("titleHello", new Date(System.currentTimeMillis()));
        EventEntity eventEntityAdded= (EventEntity) facade.createEvent(eventEntity);
        UserEntity userEntity=new UserEntity("Sergei", "Sergey@mail.ru");
        UserEntity userEntityAdded= (UserEntity) facade.createUser(userEntity);
        TicketEntity ticketEntity= (TicketEntity) facade.bookTicket(userEntityAdded.getId(),eventEntityAdded.getId(), 12, Ticket.Category.BAR);
        facade.cancelTicket(ticketEntity.getId());
        assertNotNull(eventEntityAdded);
        assertNotNull(eventEntityAdded.getId());
        assertNotNull(userEntityAdded);
        assertNotNull(userEntityAdded.getId());
        assertNotNull(ticketEntity);
        assertNotNull(ticketEntity.getId());
        assertNull(facade.getTicketById(ticketEntity.getId()));
    }
}
