package com.sumasoft.stt.audio;

import com.sumasoft.stt.client.AudioVoskClient;
import com.sumasoft.stt.response.Response;
import org.json.JSONObject;

import java.net.URI;

public class AcceptStream {

    AudioVoskClient client;
    Response response=new Response();

    public AcceptStream(int sampleRate) throws Exception{
        URI uri=(new URI("ws://192.168.100.37:2700"));
        client=new AudioVoskClient(uri);
        client.connectBlocking();
        System.out.println("Client & Server connected sucessfully");

        //send configuration
        JSONObject outer=new JSONObject();
        JSONObject conf=new JSONObject();
        outer.put("config",conf.put("sample_rate",sampleRate));
        //  outer.put("config",conf.put("num_channels", 1));
        client.send(outer.toString());
    }
    
    
    public void acceptStream(byte[] b){
        client.send(b);
    }
}
