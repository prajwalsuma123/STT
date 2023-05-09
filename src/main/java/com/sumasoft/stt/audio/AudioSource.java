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
    public static AudioFormat getAudioFormat(){
        AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 60000, 16, 1, 1, 8000.0f, false);
        return format;
    }

}
