package com.sumasoft.stt.client;

import com.sumasoft.stt.result.Channel;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class AudioClient extends WebSocketClient {
    public Channel channel;
    
    public AudioClient(URI serverUri,Channel channel) {
        super(serverUri);
        this.channel=channel;
    }
    
    
    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("WebSocket opened: " + serverHandshake.getHttpStatus() + " " + serverHandshake.getHttpStatusMessage());
        System.out.println("Connection established");
    }

    @Override
    public void onMessage(String s) {
       // System.out.println("Message recived from Server :"+s.toString());
        channel.notifySubscriber(s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {

    }

    @Override
    public void onError(Exception e) {

    }
}
