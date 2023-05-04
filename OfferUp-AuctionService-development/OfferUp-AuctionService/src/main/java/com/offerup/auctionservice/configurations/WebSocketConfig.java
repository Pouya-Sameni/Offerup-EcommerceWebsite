package com.offerup.auctionservice.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.offerup.auctionservice.dutchAuctionServices.QueryDutchService;
import com.offerup.auctionservice.forwardAuctionServices.QueryForwardService;
import com.offerup.auctionservice.repos.DutchAuctionDB;
import com.offerup.auctionservice.repos.ForwardAuctionDB;
import com.offerup.auctionservice.websockethandlers.DutchWSHandler;
import com.offerup.auctionservice.websockethandlers.ForwardWSHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@ComponentScan("com.offerup.auctionservice")
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private DutchAuctionDB dutchAuctionDB;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private ForwardAuctionDB forwardAuctionDB;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(dutchHandler(), "/dutch/{auctionId}/update")
                .setAllowedOrigins("*");
        registry.addHandler(forwardHandler(), "/forward/{auctionId}/update")
                .setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler dutchHandler() {
        return new DutchWSHandler(dutchAuctionDB, objectMapper);
    }

    @Bean
    public WebSocketHandler forwardHandler() {
        return new ForwardWSHandler(forwardAuctionDB, objectMapper);
    }

}

