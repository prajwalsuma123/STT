package com.sumasoft.stt.client;

import com.sumasoft.stt.result.Channel;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URI;

public class AudioClient extends WebSocketClient {
    public static final Logger logger= LoggerFactory.getLogger(AudioClient.class);
    public Channel channel;
    
    public AudioClient(URI serverUri,Channel channel) {
        super(serverUri);
        this.channel=channel;
    }
    
    
    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        logger.info("WebSocket opened: " + serverHandshake.getHttpStatus() + " " + serverHandshake.getHttpStatusMessage());
    }

    @Override
    public void onMessage(String s) {
//        logger.info("Message from Server :"+s.toString());
        this.channel.notifySubscriber(s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {

    }

    @Override
    public void onError(Exception e) {

    }
}
