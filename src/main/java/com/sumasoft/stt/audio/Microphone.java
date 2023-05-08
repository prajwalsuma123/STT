package com.sumasoft.stt.audio;

import com.sumasoft.stt.client.AudioClientVosk;
import com.sumasoft.stt.utils.ExecutionContext;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sound.sampled.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *This class is used to send audio data to vosk server 
 * using microphone as an input
 */

public class Microphone implements AudioSource{

    public static final Logger logger= LoggerFactory.getLogger(Microphone.class);
    public float sampleRate=8000.0f;
    public int CHUNK_SIZE=1024;
    byte[] b = new byte[4096];
    int bytesRead=0;
    int numBytesRead;

    public AudioClientVosk audioClientVosk;

    @Autowired
    ExecutionContext executionContext;

    AudioFormat format = AudioSource.getAudioFormat();
    public DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
    public TargetDataLine microphone;


    /**
     * This method initialize Aunthenticat
     * @throws LineUnavailableException
     * @throws URISyntaxException
     * @throws InterruptedException
     */
    public void initialize() throws LineUnavailableException, URISyntaxException, InterruptedException {
        logger.info("initializing microphone");
        microphone = (TargetDataLine) AudioSystem.getLine(info);
        microphone.open(format);
        microphone.start();
        this.audioClientVosk=new AudioClientVosk(new URI("ws://192.168.100.37:2700"));
        this.audioClientVosk.connectBlocking();
        logger.info("Audiovosk client connected sucessfully");
        send();
    }

    /**
     * Send the Audio data to vosk server
     */
    private void send() {

        while (bytesRead <= 100000000) {
            numBytesRead = microphone.read(b, 0, CHUNK_SIZE);
            bytesRead += numBytesRead;

            /**
             * Send byte aaray
             */
            logger.info("sending to to vosk server");
            this.audioClientVosk.send(b);

        }
    }

    /**
     * Stop the microphone
     */
    public void stop(){
        microphone.close();
    }




}
