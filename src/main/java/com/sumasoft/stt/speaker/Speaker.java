package com.sumasoft.stt.speaker;

import javax.sound.sampled.*;

public class Speaker {

    public float sampleRate=8000.0f;
    AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sampleRate, 16, 1, 2, 8000.0f, false);
    public DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
    public SourceDataLine speakers;

    public void initilaize() throws LineUnavailableException {
        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
        speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
        speakers.open(format);
        speakers.start();
    }

    public void stop(){
        speakers.drain();
        speakers.close();
    }
}
