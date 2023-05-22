package com.sumasoft.stt.audio;

import com.sumasoft.stt.client.AudioClient;
import com.sumasoft.stt.result.Channel;
import com.sumasoft.stt.result.Subscriber;
import org.json.JSONObject;

import java.net.URI;

public class AcceptAudio {

    AudioClient client;
    Channel channel;

    public AcceptAudio(int sampleRate, Subscriber subscriber) throws Exception{
        this.channel=new Channel();
        addSubscriber(subscriber);
        URI uri=(new URI("ws://192.168.100.37:2700"));
        client=new AudioClient(uri,channel);
        client.connectBlocking();
        System.out.println("Client & Server connected sucessfully");

        //send configuration
        JSONObject outer=new JSONObject();
        JSONObject conf=new JSONObject();
        outer.put("config",conf.put("sample_rate",sampleRate));
        //  outer.put("config",conf.put("num_channels", 1));
        client.send(outer.toString());
    }
    public void addSubscriber(Subscriber subscriber){
        this.channel.subscriber(subscriber);
    }

    public void acceptStream(byte[] b){
        client.send(b);
    }
}
