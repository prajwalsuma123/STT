package com.sumasoft.stt.audio;

import com.sumasoft.stt.client.AudioVoskClient;
import org.json.JSONObject;

import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class Microphone {
    public float sampleRate=60000;
    //  AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sampleRate, 16, 1, 2, 8000.0f, false);
    AudioFormat format = new AudioFormat(sampleRate, 16, 1,  true, false);
    public DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

    public TargetDataLine microphone;
    public SourceDataLine speakers;



    public void startMicrophone(){
    }
}