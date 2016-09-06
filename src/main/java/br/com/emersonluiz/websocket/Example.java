package br.com.emersonluiz.websocket;

import javax.websocket.CloseReason;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/example")
public class Example {

    @OnMessage
    public String message(String message) {
        return "The websocket message is: " + message;
    }

    @OnOpen
    public void open(Session session) {
        System.out.println("webSocket opened: " + session.getId());
    }

    public void close(CloseReason  reason) {
        System.out.println("WebSocket connection closed with CloseCode: " + reason.getCloseCode());
    }
}
