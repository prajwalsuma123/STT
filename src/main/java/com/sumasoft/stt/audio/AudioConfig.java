package com.sumasoft.stt.audio;

import com.sumasoft.stt.utils.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

public class AudioConfig {

    @Autowired
    public Microphone microphone;
    
    //public ExecutionContext executionContext;

    public AudioConfig(){

    }

    public void fromMicrophone(ExecutionContext executionContext){

        try {
            microphone.initialize();
        }
        catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }

    }

    public void fromAudioStream(){

    }

    public void fromAudioFile(){

    }
}
