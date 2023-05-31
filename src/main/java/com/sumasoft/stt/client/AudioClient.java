package com.sumasoft.stt.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumasoft.stt.result.ResultNotifiable;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class AudioClient extends WebSocketClient {
    public static final Logger logger= LoggerFactory.getLogger(AudioClient.class);
    ResultNotifiable resultNotifiable;
    ObjectMapper objectMapper;
    public AudioClient(URI serverUri, ResultNotifiable resultNotifiable) {
        super(serverUri);
        this.resultNotifiable=resultNotifiable;
        this.objectMapper= new ObjectMapper();
    }


    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        logger.info("Connection opened: " + serverHandshake.getHttpStatus() + " " + serverHandshake.getHttpStatusMessage());
    }

    @Override
    public void onMessage(String s) {

        try {
            JsonNode jsonNode = objectMapper.readTree(s);
            jsonNode.has("result");
            if(jsonNode.has("result")){
                this.resultNotifiable.resultText(s);
            }
            else {
                this.resultNotifiable.partialText(s);

            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onClose(int i, String s, boolean b) {

    }

    @Override
    public void onError(Exception e) {
        logger.info("Error occured while speech-to-Text");
    }
}