package com.sumasoft.stt.client;

import com.sumasoft.stt.SpeachToTextApplication;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class AudioClient extends WebSocketClient {
    public static final Logger logger= LoggerFactory.getLogger(AudioClient.class);

    public AudioClient(URI serverUri) {
        super(serverUri);
    }
    
    
    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        logger.info("Library opened: " + serverHandshake.getHttpStatus() + " " + serverHandshake.getHttpStatusMessage());
    }

    @Override
    public void onMessage(String s) {
        logger.info("Response recived from Library :"+s.toString());
    }

    @Override
    public void onClose(int i, String s, boolean b) {

    }

    @Override
    public void onError(Exception e) {

    }
}
