package com.sumasoft.stt.audio;

import com.sumasoft.stt.result.Subscriber;

import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.net.URISyntaxException;

public class Microphone {
    public float sampleRate=60000;
    //  AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sampleRate, 16, 1, 2, 8000.0f, false);
    AudioFormat format = new AudioFormat(sampleRate, 16, 1,  true, false);
    public DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

    public TargetDataLine microphone;
    public AcceptAudio acceptAudio;
    
    public Microphone(Subscriber subscriber) throws Exception {
            this.acceptAudio=new AcceptAudio((int) sampleRate,subscriber);
    }
    
    public void startMicrophone() throws InterruptedException, URISyntaxException, LineUnavailableException {
        microphone = (TargetDataLine) AudioSystem.getLine(info);
        microphone.open(format);
        microphone.start();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int numBytesRead;
        int CHUNK_SIZE =1024;
        int bytesRead = 0;

        byte[] b = new byte[1024];

        while (bytesRead <= 100000000) {
            numBytesRead = microphone.read(b, 0, CHUNK_SIZE);
            bytesRead += numBytesRead;

            /**
             * Send byte aaray
             */
            acceptAudio.send(b);

        }

    }
}
