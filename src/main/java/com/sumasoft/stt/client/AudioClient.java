package com.sumasoft.stt.client;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.*;
import com.sumasoft.stt.result.ResultNotifiable;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URI;
import java.util.TreeSet;

public class AudioClient extends WebSocketClient {
    public static final Logger logger= LoggerFactory.getLogger(AudioClient.class);
    ResultNotifiable resultNotifiable;
    JsonParser parser;
    TreeSet<String> treeSet;
    JsonObject jsonObject;

    public AudioClient(URI serverUri, ResultNotifiable resultNotifiable) {
        super(serverUri);
        this.resultNotifiable=resultNotifiable;
        this. parser= new JsonParser();
    }


    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        logger.info("Connection opened: " + serverHandshake.getHttpStatus() + " " + serverHandshake.getHttpStatusMessage());
    }

    @Override
    public void onMessage(String s) {
        JsonElement rootElement = parser.parse(s);
        jsonObject=rootElement.getAsJsonObject();
        if(jsonObject.has("partial")){
            this.resultNotifiable.partialText(s);
        } else if (jsonObject.has("text")) {
            this.resultNotifiable.resultText(s);
        }
    }

    /**
     * fliter json object and get value as per requirement
     * for e.g. if any user want only resultText not partialtext and visevarsa
     * @param jsonObject
     */
    private void filterPartialAndResult(JsonObject jsonObject) {
        if(jsonObject.has("partial")){
            //send value present inside key
            this.resultNotifiable.partialText(jsonObject.get("partial").getAsString());
        }
        if(jsonObject.has("text")){
            //send value present inside key
            this.resultNotifiable.resultText(jsonObject.get("text").getAsString());
        }
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        logger.info("Connection closed");
    }

    @Override
    public void onError(Exception e) {
        logger.info("Error Occured in library");
    }

}
