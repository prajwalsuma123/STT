package com.sumasoft.stt.audio;

import com.sumasoft.stt.result.Channel;
import com.sumasoft.stt.result.ConcretSubscriber;
import com.sumasoft.stt.result.Subscriber;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioFile {

    public String wavFilePath = "/home/prajwal.sonawane/Desktop/shivaji.wav";
    Channel channel;
    ConcretSubscriber subscriber;
    AcceptAudio acceptAudio;

    public AudioFile(ConcretSubscriber subscriber) throws Exception {
        this.subscriber=subscriber;
    }
    public AudioFile() throws Exception {
        this.subscriber=subscriber;
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

            this.acceptAudio=new AcceptAudio((int) audioFormat.getSampleRate(),this.subscriber);
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
                acceptAudio.send(buffer);
            }

            // Wait for playback to complete
            line.drain();

            // Close the audio line
            line.close();
            audioInputStream.close();
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

