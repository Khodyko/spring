package org.example.spring;

import org.example.spring.facade.FacadeImpl;
import org.example.spring.model.Entity.EventEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Date;


public class Runner {
    public Runner() {

    }

    public static void main(String[] args) {
        ApplicationContext ctx= new ClassPathXmlApplicationContext("applicationContext.xml");

        FacadeImpl facade=(FacadeImpl) ctx.getBean("facadeImpl");
        EventEntity eventEntity=new EventEntity(5,"titleHello", new Date(System.currentTimeMillis()));
        facade.createEvent(eventEntity);
        System.out.println(facade.getEventById(5));
//        Map<String, EventEntity> eventMap = runner.readFileJson(pathToEventJsonFile, EventEntity.class);
//        Map<String, UserEntity> userMap = runner.readFileJson(pathToUserJsonFile, UserEntity.class);
//        Map<String, TicketEntity> ticketMap = runner.readFileJson(pathToTicketJsonFile, TicketEntity.class);
//        for (Map.Entry<String, EventEntity> entry : eventMap.entrySet()) {
//            System.out.println(entry.getKey() + "=" + entry.getValue());
//        }
    }

//    private final void addEventMap() {
//        Event event;
//        String title;
//        long dayMillis = 864000000;
//        Date date;
//        for (int i = 0; i < 10; i++) {
//            date = new Date(System.currentTimeMillis() + i * dayMillis);
//            title = "Title: " + i + " days after today";
//            event = new EventEntity(i, title, date);
//            eventMap.put("event" + i, event);
//        }
//    }
//
//    private final void addUserMap() {
//        User user;
//        String name;
//        String email;
//
//        for (int i = 0; i < 10; i++) {
//
//            name = "Sergey";
//            email = name + i + "@mail.ru";
//            user = new UserEntity(i, name, email);
//            userMap.put("user" + i, user);
//        }
//    }
//
//    private final void addTicketMap() {
//    }

}
