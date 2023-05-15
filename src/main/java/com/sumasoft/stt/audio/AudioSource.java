package com.sumasoft.stt.audio;

import javax.sound.sampled.AudioFormat;

/**
 * Interface is used for Audio source
 * e.g. StreamAudio,Microphone,etc.
 */
public interface AudioSource {
    /**
     * Get the Audioformate
     * @return
     */
    public float sampleRate=16000;
    public static AudioFormat getAudioFormat(){
        //AudioFormat format = new AudioFormat( 160000, 16,1,  true, false);
        AudioFormat format = new AudioFormat(sampleRate, 16, 1,  true, false);

        return format;
    }

}
