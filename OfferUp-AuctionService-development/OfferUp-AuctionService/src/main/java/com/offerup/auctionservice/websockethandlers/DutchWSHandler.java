package com.offerup.auctionservice.websockethandlers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.offerup.auctionservice.dtos.DutchAuction;
import com.offerup.auctionservice.dutchAuctionServices.QueryDutchService;
import com.offerup.auctionservice.forwardAuctionServices.QueryForwardService;
import com.offerup.auctionservice.repos.DutchAuctionDB;
import com.offerup.auctionservice.repos.ForwardAuctionDB;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class DutchWSHandler extends TextWebSocketHandler {



    ObjectMapper objectMapper;



    private static Map<String, Set<WebSocketSession>> auctionSessions = new HashMap<>();

    DutchAuctionDB dutchAuctionDB;

    public DutchWSHandler (DutchAuctionDB dutchAuctionDB, ObjectMapper objectMapper)
    {
        this.dutchAuctionDB = dutchAuctionDB;
        this.objectMapper = objectMapper;
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String auctionID = getAuctionID(session);
        System.out.println(auctionID);

        if (!auctionSessions.containsKey(auctionID)) {

            auctionSessions.put(auctionID, new HashSet<>());
        }
        auctionSessions.get(auctionID).add(session);
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(dutchAuctionDB.findByAuctionId(new ObjectId(auctionID)))));

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String auctionID = getAuctionID(session);
        Set<WebSocketSession> sessions = auctionSessions.get(auctionID);
        if (sessions != null) {
            sessions.remove(session);
            if (sessions.isEmpty()) {
                auctionSessions.remove(auctionID);
            }
        }
    }

    public void broadcast(String auctionID, DutchAuction message) throws IOException {
        Set<WebSocketSession> sessions = auctionSessions.get(auctionID);
        if (sessions != null) {
            for (WebSocketSession session : sessions) {
                session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
            }
        }
    }
    private String getAuctionID(WebSocketSession session) {
        String [] path = session.getUri().getPath().split("/");
        return path[path.length - 2];
    }



}
