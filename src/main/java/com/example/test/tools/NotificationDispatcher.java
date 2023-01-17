package com.example.test.tools;

import com.example.test.dto.communication.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.HashSet;
import java.util.Set;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

@Service
public class NotificationDispatcher {
    private
    Set<String> listeners = new HashSet<>();
    SimpMessagingTemplate template;

    @Autowired
    public NotificationDispatcher(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Scheduled(fixedDelay = 300000, initialDelay = 900000)
    public void dispatch() {
        for (String listener : listeners) {
            LOGGER.info("Sending notification to " + listener);
            SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
            headerAccessor.setSessionId(listener);
            headerAccessor.setLeaveMutable(true);
            //todo moze li iz session id-a da se dobije korisnik? i onda nadjemo voznju i ispisemo scheduled time za
            // sve voznje jer set nema duplikata (ako ima vise rezervisanih voznji, nece se dodati vise puta)
            String value = "You have a scheduled ride";
            template.convertAndSendToUser(
                    listener,
                    "/notification/item",
                    new Notification(value),
                    headerAccessor.getMessageHeaders());
        }
    }

    public void add(String session) {
        this.listeners.add(session);
    }

    public void remove(String session) {
        this.listeners.remove(session);
    }

    @EventListener
    public void sessionDisconnectionHandler(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        LOGGER.info("Disconnecting " + sessionId + "!");
        remove(sessionId);
    }
}
