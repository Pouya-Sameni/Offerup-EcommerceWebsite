package com.offerup.auctionservice.websockethandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.offerup.auctionservice.dtos.ForwardAuction;
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
public class ForwardWSHandler extends TextWebSocketHandler {

    private static Map<String, Set<WebSocketSession>> auctionSessions = new HashMap<>();

    ObjectMapper objectMapper;


    ForwardAuctionDB forwardAuctionDB;

    public ForwardWSHandler (ForwardAuctionDB forwardAuctionDB, ObjectMapper objectMapper)
    {
        this.forwardAuctionDB = forwardAuctionDB;
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String auctionID = getAuctionID(session);
        System.out.println(auctionID);
        if (!auctionSessions.containsKey(auctionID)) {

            auctionSessions.put(auctionID, new HashSet<>());
        }
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(forwardAuctionDB.findByAuctionId(new ObjectId(auctionID)))));
        auctionSessions.get(auctionID).add(session);

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

    public void broadcast(String auctionID, ForwardAuction message) throws IOException {

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
