package com.example.test.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

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
}