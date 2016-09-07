package br.com.emersonluiz.websocket;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/example")
public class Example {

    private final static Logger logger = Logger.getLogger(Example.class.getName());

    @OnMessage
    public String message(String message, Session session) {
        try {
            //broadcast
            for (Session s : session.getOpenSessions()) {
                s.getBasicRemote().sendText("Someone interected...");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.toString(), e);
        }

        logger.info("Send message");
        return "The websocket message is: " + message;
    }

    @OnOpen
    public void open(Session session) {
        logger.info("WebSocket opened: " + session.getId());
        message(session);
    }

    @OnClose
    public void close(CloseReason  reason) {
        logger.info("WebSocket closed with CloseCode: " + reason.getCloseCode());
    }

    @OnError
    public void error(Throwable t) {
        logger.log(Level.SEVERE, t.getMessage(), t);
    }

    private void message(Session session) {
        session.getAsyncRemote().sendText("listening...");
    }
}
