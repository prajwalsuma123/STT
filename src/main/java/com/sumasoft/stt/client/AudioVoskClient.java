package com.sumasoft.stt.client;

import com.sumasoft.stt.response.Response;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public abstract class AudioVoskClient extends WebSocketClient {

    public AudioVoskClient(URI serverUri) {
        super(serverUri);
    }
}