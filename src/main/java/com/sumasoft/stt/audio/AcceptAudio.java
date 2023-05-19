package com.sumasoft.stt.audio;

import com.sumasoft.stt.client.AudioClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class AcceptAudio {
    public static final Logger logger= LoggerFactory.getLogger(AcceptAudio.class);


    AudioClient client;

    public AcceptAudio(int sampleRate) throws Exception{
        URI uri=(new URI("ws://192.168.100.37:2700"));
        client=new AudioClient(uri);
        client.connectBlocking();

        //send configuration
        JSONObject outer=new JSONObject();
        JSONObject conf=new JSONObject();
        outer.put("config",conf.put("sample_rate",sampleRate));
        client.send(outer.toString());
    }
    
    
    public void send(byte[] b){
        client.send(b);
    }
}
