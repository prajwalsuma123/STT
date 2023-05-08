package com.sumasoft.stt.client;

import java.net.URI;

import com.sumasoft.stt.audio.Microphone;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Socket connection class
 */
public class AudioClientVosk extends WebSocketClient{
    public static final Logger logger= LoggerFactory.getLogger(AudioClientVosk.class);

    public AudioClientVosk(URI serverURI) {
        super(serverURI);
        logger.info("inside AudioClientVosk with URI:"+serverURI);
    }

    /**
     *method gets called when connection gets established
     * @param serverHandshake
     */
    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("WebSocket opened: " + serverHandshake.getHttpStatus() + " " + serverHandshake.getHttpStatusMessage());
        logger.info("Web Socket connection established");
        JSONObject outer=new JSONObject();
        JSONObject conf=new JSONObject();
        outer.put("config",conf.put("sample_rate",44100));
        outer.put("config",conf.put("num_channels", 1));
        this.send(outer.toString());
    }

    /**
     * method gets called when server sends any response
     * @param s
     */
    @Override
    public void onMessage(String s) {
        logger.info("Message recived from server :"+s);
        System.out.println("Meassage form server :"+s);
    }

    /**
     * method gets called when server connection closed
     * @param i
     * @param s
     * @param b
     */
    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println("Web Socket Connection close");

    }

    /**
     * Method get's call when something error happens on server side
     * @param e
     */
    @Override
    public void onError(Exception e) {
        System.out.println("Web Socket connection Error :");
        e.printStackTrace();
    }
}
