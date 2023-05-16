package com.sumasoft.stt.audio;


import com.sumasoft.stt.client.AudioVoskClient;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class AudioConfig {
    
    AudioVoskClient client;
    
    public AudioConfig(){
        try
        {
//            URI uri=(new URI("ws://192.168.100.37:2700"));
//            this.client=new AudioVoskClient();
//            client.connectBlocking();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    
    }
    
    public void sendByteArray(byte[] bytes){
        
        this.client.send(bytes);
    }
}
