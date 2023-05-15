package com.sumasoft.stt;


import com.sumasoft.stt.client.AudioVoskClient;

import javax.sound.sampled.*;
import java.io.*;
import java.net.*;


/**
 * This contain working code.
 * we can depricate this class once we get final implementation
 * Takes input from microphone and sends to Vosk Server
 */
public class SpeechRecognizer {

    public float sampleRate=8000.0f;
    AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sampleRate, 16, 1, 2, 8000.0f, false);
    public DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

    public TargetDataLine microphone;
    public SourceDataLine speakers;

    public SpeechRecognizer() throws IOException, URISyntaxException {
    }

    /**
     * start the speach recongnizing
     */
    public void start() {
        try {

            try {

                try {
                    /**
                     * initialize microphone
                     */
                    microphone = (TargetDataLine) AudioSystem.getLine(info);
                    microphone.open(format);
                    microphone.start();



                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    int numBytesRead;
                    int CHUNK_SIZE = 1024;
                    int bytesRead = 0;

                    /**
                     * initialize speaker for callback
                     */
                    DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
                    speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                    speakers.open(format);
                    speakers.start();


                    /**
                     * create byte aaray to send audio data to server
                     */
                    byte[] b = new byte[4096];

                    /**
                     * Client object creation
                     */
                    URI uri=new URI("ws://192.168.100.37:2700");
                    AudioVoskClient client = new AudioVoskClient(uri);

                    client.connectBlocking();

                    while (bytesRead <= 100000000) {
                        numBytesRead = microphone.read(b, 0, CHUNK_SIZE);
                        bytesRead += numBytesRead;

                        out.write(b, 0, numBytesRead);

                        /**
                         * Send byte aaray
                         */
                        client.send(b);

                        speakers.write(b, 0, numBytesRead);
                    }

                    speakers.drain();
                    speakers.close();
                    microphone.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void fromMicroPhone()
    {

    }

}

