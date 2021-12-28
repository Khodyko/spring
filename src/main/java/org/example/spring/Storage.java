package org.example.spring;


import org.example.spring.model.Entity.EventEntity;
import org.example.spring.model.Entity.TicketEntity;
import org.example.spring.model.Entity.UserEntity;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Storage implements BeanPostProcessor, Serializable {
    private static final long serialVersionUID = 1L;
    private Map<String, TicketEntity> ticketMap = new HashMap();
    private Map<String, UserEntity> userMap = new HashMap();
    private Map<String, EventEntity> eventMap = new HashMap();
    @Value("${event.file.path}")
    String eventFilePath;
    @Value("${ticket.file.path}")
    String ticketFilePath;
    @Value("${user.file.path}")
    String userFilePath;

    public Storage() {
    }

    public Map<String, TicketEntity> getTicketMap() {
        return ticketMap;
    }

    public void setTicketMap(Map<String, TicketEntity> ticketMap) {
        this.ticketMap = ticketMap;
    }

    public Map<String, UserEntity> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<String, UserEntity> userMap) {
        this.userMap = userMap;
    }

    public Map<String, EventEntity> getEventMap() {
        return eventMap;
    }

    public void setEventMap(Map<String, EventEntity> eventMap) {
        this.eventMap = eventMap;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

       if(bean instanceof JsonReader){
       eventMap=((JsonReader)bean).readFileJson(eventFilePath, EventEntity.class);
       userMap=((JsonReader)bean).readFileJson(userFilePath, UserEntity.class);
       ticketMap=((JsonReader)bean).readFileJson(ticketFilePath, TicketEntity.class);
       }
        return bean;
    }

//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("Storage{");
////        sb.append("ticketMap=").append(ticketMap);
////        sb.append(", userMap=").append(userMap);
//        sb.append(", eventMap=").append(eventMap.get(0));
//        sb.append('}');
//        return sb.toString();
//    }


}
