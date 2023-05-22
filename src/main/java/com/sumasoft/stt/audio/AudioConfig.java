package com.sumasoft.stt.audio;


import com.sumasoft.stt.client.AudioClient;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class AudioConfig {

    AudioClient client;
    
    public AudioConfig(){
        try
        {
            URI uri=(new URI("ws://192.168.100.37:2700"));
         //   this.client=new AudioVoskClient(uri);
      //      client.connectBlocking();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    
    }
    
    public void sendByteArray(byte[] bytes){
        
        this.client.send(bytes);
    }
}
