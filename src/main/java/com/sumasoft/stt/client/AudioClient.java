package com.sumasoft.stt.client;

import com.sumasoft.stt.result.AbstractNotifiable;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URI;

public class AudioClient extends WebSocketClient {
    public static final Logger logger= LoggerFactory.getLogger(AudioClient.class);
    AbstractNotifiable abstractNotifiable;
    public AudioClient(URI serverUri, AbstractNotifiable abstractNotifiable) {
        super(serverUri);
        this.abstractNotifiable=abstractNotifiable;
    }
    
    
    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        logger.info("WebSocket opened: " + serverHandshake.getHttpStatus() + " " + serverHandshake.getHttpStatusMessage());
    }

    @Override
    public void onMessage(String s) {
//        logger.info("Message from Server :"+s.toString());
        if(s.contains("result")){
            this.abstractNotifiable.resultText(s);
        }
        else {
            this.abstractNotifiable.partialText(s);
        }
    }

    @Override
    public void onClose(int i, String s, boolean b) {

    }

    @Override
    public void onError(Exception e) {

    }
}
