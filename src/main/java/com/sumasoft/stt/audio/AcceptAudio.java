package com.sumasoft.stt.audio;

import com.sumasoft.stt.client.AudioClient;
import com.sumasoft.stt.result.ResultNotifiable;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class AcceptAudio {
    public static final Logger logger= LoggerFactory.getLogger(AcceptAudio.class);
    AudioClient client;
    
    public AcceptAudio(int sampleRate, ResultNotifiable resultNotifiable) throws Exception{
        getSysteminfo();
        URI uri=(new URI("ws://192.168.100.37:2700"));
        client=new AudioClient(uri,resultNotifiable);
        client.connectBlocking();
        System.out.println("Client & Server connected sucessfully");
        logger.info("Connected sucessfully");

        //send configuration
        JSONObject outer=new JSONObject();
        JSONObject conf=new JSONObject();
        outer.put("config",conf.put("sample_rate",sampleRate));
        //  outer.put("config",conf.put("num_channels", 1));
        client.send(outer.toString());
    }
    
    public void send(byte[] b){
        client.send(b);
    }
    private static void getSysteminfo() {
        logger.info("--------------------- System Information ---------------------");
        logger.info("Operating system :"+System.getProperty("os.name"));
        logger.info("Java version :"+System. getProperty("java.version"));
        logger.info("Operating system architecture :"+System.getProperty("os.arch"));
        logger.info("Java Home :"+System.getProperty("java.home"));
        logger.info("System username :"+System.getProperty("user.name"));
        logger.info("--------------------- System Information ---------------------");

    }
    
 
}
