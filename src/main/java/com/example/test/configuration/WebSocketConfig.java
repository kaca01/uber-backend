package com.example.test.configuration;

import org.apache.tomcat.websocket.server.WsSci;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import javax.naming.Context;
import java.util.Collections;

@Configuration
@EnableWebSocketMessageBroker
@CrossOrigin
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    //dodavanje tema
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/notification");  //teme
        registry.enableSimpleBroker("/map-updates");  //teme
        //registry.setApplicationDestinationPrefixes("/app"); //kada front salje apije, slace sa ovim prefiksom

        registry.setApplicationDestinationPrefixes("/socket-subscriber") // Prefiks koji koji se koristi za mapiranje svih poruka.
                // Klijenti moraju da ga navedu kada salju poruku serveru.
                // Svaki URL bi pocinjao ovako: http://localhost:8080/socket-subscriber/…/…
                .enableSimpleBroker("/socket-publisher"); // Definisanje topic-a (ruta) na koje klijenti mogu da se pretplate.
        // SimpleBroker cuva poruke u memoriji i salje ih klijentima na definisane topic-e.
        // Server kada salje poruke, salje ih na rute koje su ovde definisane, a klijenti cekaju na poruke.
        // Vise ruta odvajamo zarezom, npr. enableSimpleBroker("/ruta1", "/ruta2");

    }

    // otvaranje konekcije
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/socket")
                .setAllowedOrigins("http://localhost:4200", "http://127.0.0.1:4200")
                .withSockJS();
        registry.addEndpoint("/notification")
                .setAllowedOrigins("http://localhost:4200", "http://127.0.0.1:4200")
                .withSockJS();

        registry.addEndpoint("/notif") // Definisemo endpoint koji ce klijenti koristiti da se povezu sa serverom.
                // U ovom slucaju, URL za konekciju ce biti http://localhost:8080/socket/
                .setAllowedOrigins("http://localhost:4200", "http://127.0.0.1:4200") // Dozvoljavamo serveru da prima zahteve bilo kog porekla
                .withSockJS(); // Koristi se SockJS: https://github.com/sockjs/sockjs-protocol
    }

    @Bean
    public TomcatServletWebServerFactory tomcatContainerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();;
        factory.setTomcatContextCustomizers(Collections.singletonList(tomcatContextCustomizer()));
        return factory;
    }

    @Bean
    public TomcatContextCustomizer tomcatContextCustomizer() {
        return new TomcatContextCustomizer() {
            @Override
            public void customize(org.apache.catalina.Context context) {
                context.addServletContainerInitializer(new WsSci(), null);
            }
        };
    }
}