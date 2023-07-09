package com.StudentLoginv01.StudentLoginv01.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Configuration
public class Config {

    @Bean
    public WebSocketHandler myWebSocketHandler() {
        return new TextWebSocketHandler();
    }
}
