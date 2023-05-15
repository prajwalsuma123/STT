package com.sumasoft.stt.audio;

import com.sumasoft.stt.audio.AcceptStream;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioFile {

    public String wavFilePath = "/home/prajwal.sonawane/Desktop/shivaji.wav";
    AcceptStream acceptStream=new AcceptStream(16000);

    public AudioFile() throws Exception {
    }

    public void sendAudio(){

        try {
            // Load the .wav file
            AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(new File(wavFilePath));
                AudioFormat format=audioInputStream.getFormat();
            } catch (UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            // Get the audio format from the file
            AudioFormat audioFormat = audioInputStream.getFormat();

            // Open the audio line for playback
            SourceDataLine line = AudioSystem.getSourceDataLine(audioFormat);
            line.open(audioFormat);

            // Start playback
            line.start();

            // Read audio data from the file and play it back
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = audioInputStream.read(buffer)) != -1) {
                line.write(buffer, 0, bytesRead);
                acceptStream.acceptStream(buffer);
            }

            // Wait for playback to complete
            line.drain();

            // Close the audio line
            line.close();
            audioInputStream.close();
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}

