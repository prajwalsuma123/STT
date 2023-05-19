package com.sumasoft.stt.audio;

import com.sumasoft.stt.client.AudioClient;
import org.json.JSONObject;

import java.net.URI;

public class AcceptAudio {

    AudioClient client;

    public AcceptAudio(int sampleRate) throws Exception{
        URI uri=(new URI("ws://192.168.100.37:2700"));
        client=new AudioClient(uri);
        client.connectBlocking();
        System.out.println("Client & Server connected sucessfully");

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
