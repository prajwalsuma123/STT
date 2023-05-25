package com.sumasoft.stt.client;

import com.sumasoft.stt.result.ResultNotifiable;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URI;

public class AudioClient extends WebSocketClient {
    public static final Logger logger= LoggerFactory.getLogger(AudioClient.class);
    ResultNotifiable resultNotifiable;
    public AudioClient(URI serverUri, ResultNotifiable resultNotifiable) {
        super(serverUri);
        this.resultNotifiable=resultNotifiable;
    }
    
    
    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        logger.info("WebSocket opened: " + serverHandshake.getHttpStatus() + " " + serverHandshake.getHttpStatusMessage());
    }

    @Override
    public void onMessage(String s) {
        if(s.contains("result")){
            this.resultNotifiable.resultText(s);
        }
        else {
            this.resultNotifiable.partialText(s);
        }
    }

    @Override
    public void onClose(int i, String s, boolean b) {

    }

    @Override
    public void onError(Exception e) {

    }
}
