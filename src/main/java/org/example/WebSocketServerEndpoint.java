package org.example;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.nio.SelectorHandler;

import javax.websocket.*;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
//import java.util.logging.Logger;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.*;


@ServerEndpoint(value = "/game")

public class WebSocketServerEndpoint {
//    private Logger logger = Logger.getLogger(this.getClass().getName());
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private  static int i =0;

    @OnOpen
    public void onOpen(Session session) throws IOException, InterruptedException {

//        logger.info("Connected ... " + session.getId());
        System.out.println("\nConnected ... " + session.getId());
        String data = "{\"object_type\":\"Alert\",\"object_type_version\":4,\"sender_uuids\":[\"bbb74874-4c17-4892-9846-3bd7b8a8ba54\"],\"alert_type\":\"mech_digging\",\"time\":\"2023-01-18T09:00:42.821Z\",\"num_properties\":0,\"property_names\":[],\"property_values\":[],\"id\":5107,\"organization_id\":1,\"resolved_flag\":0,\"acknowledged_flag\":0,\"deleted_flag\":0,\"internal\":{\"uuid\":\"8f26d5bc-b24b-4e82-bafd-68894923feeb\",\"threat_level\":\"red\",\"status\":\"new\",\"time\":\"2023-01-17T23:30:21Z\",\"is_suppressed\":false,\"details\":{\"alert-source\":\"H3088\",\"helios_unit_id\":4,\"threatUUID\":\"4fd366e5-f2c6-4a85-95b0-ee272c033ea0\",\"event_id\":527954,\"fibre_line_name\":\"Fiber_Line_1\",\"fibre_line_id\":1,\"event_type\":\"i18n: model.event_type.description.mech_digging\",\"position\":1901.0},\"latitude\":53.183856040845903,\"longitude\":-0.56611324244670402,\"alert_responses\":[{\"time\":\"2023-01-17T23:30:21Z\",\"response\":\"received\"},{\"time\":\"2023-01-17T23:30:48Z\",\"response\":\"escalate\",\"comments\":\"i18n: alert.comments.escalated_amber\"},{\"time\":\"2023-01-17T23:34:27Z\",\"response\":\"escalate\",\"comments\":\"i18n: alert.comments.escalated_red\"},{\"time\":\"2023-01-18T09:00:38Z\",\"response\":\"confirm\"}]}}\n";
//        ScheduledFuture<?> countdown = scheduler.schedule(new Runnable() {
//            @Override
//            public void run() {
//                // do the thing
//            }}, 10, TimeUnit.SECONDS);

        while(true) {
            System.out.println("Sending message " + data.length());
            session.getBasicRemote().sendText(String.format(data), true);
            Thread.sleep(100);

        }


    }

    @OnMessage
    public void onMessage(String message, Session session,boolean last) {

        System.out.println("Message Received. Session id: " + session.getId() + ", " + "Message: " + message);
        try {
            session.getBasicRemote().sendText("Message received: " + message,last);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @OnClose
    public void onClose(Session session, CloseReason closeReason) {

//        logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
        System.out.println(String.format("Session %s closed because of %s", session.getId(), closeReason));

    }

}

