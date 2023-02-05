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
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    //dodavanje tema
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //registry.enableSimpleBroker("/notification");  //teme
        registry.enableSimpleBroker("/map-updates");  //teme
        //registry.setApplicationDestinationPrefixes("/app"); //kada front salje apije, slace sa ovim prefiksom
    }

    // otvaranje konekcije
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/socket")
                .setAllowedOrigins("http://localhost:4200", "http://127.0.0.1:4200")
                .withSockJS();
/*        registry.addEndpoint("/notifications")
                .setAllowedOrigins("http://localhost:4200", "http://127.0.0.1:4200")
                .withSockJS();*/
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