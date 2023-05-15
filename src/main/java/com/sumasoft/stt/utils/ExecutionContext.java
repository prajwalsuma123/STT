package com.sumasoft.stt.utils;


import com.sumasoft.stt.client.AudioVoskClient;
import com.sumasoft.stt.config.AppConfig;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * This class is to store the ExecutionContext throughout the execution
 */
@Service
public class ExecutionContext {

    public AudioVoskClient audioClientVosk;

    public SpeechConfig speechConfig;
    
    public AppConfig appConfig;
    
    public ExecutionContext() throws URISyntaxException {
        
    }
    public AppConfig getAppConfig() {
        if(this.appConfig!=null){
            return this.appConfig=new AppConfig();
        }
        return new AppConfig();
    }

    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }


    public SpeechConfig getSpeechConfig() {
        return speechConfig;
    }

    public void setSpeechConfig(SpeechConfig speechConfig) {
        this.speechConfig = speechConfig;
    }

    public AudioVoskClient getAudioClientVosk(URI uri) {
        if(this.audioClientVosk!=null){
            return this.audioClientVosk=new AudioVoskClient(uri);
        }
        return null;
    }

    public void setAudioClientVosk(AudioVoskClient audioClientVosk) {
        this.audioClientVosk = audioClientVosk;
    }
}
