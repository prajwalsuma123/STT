package com.sumasoft.stt.client;

import com.sumasoft.stt.response.Response;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class AudioVoskClient extends WebSocketClient {
    
    public AudioVoskClient(URI serverUri) {
        super(serverUri);
    }
    
    
    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("WebSocket opened: " + serverHandshake.getHttpStatus() + " " + serverHandshake.getHttpStatusMessage());
        System.out.println("Connection established");
    }

    @Override
    public void onMessage(String s) {
        System.out.println("Message recived from Server :"+s.toString());
    }

    @Override
    public void onClose(int i, String s, boolean b) {

    }

    @Override
    public void onError(Exception e) {

    }
}
