package org.example;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioManager {
    private TargetDataLine targetDataLine;
    private AudioFormat audioFormat;
    private DataLine.Info info;
    private boolean isRecording = false;
    private RecordingSession recordingSession;

    public AudioManager(RecordingSession recordingSession) {
        this.recordingSession = recordingSession;
    }

    // Set up the audio format
    private void setupAudioFormat() {
        audioFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                44100.0F,
                16,
                2,
                4,
                44100.0F,
                false
        );
        info = new DataLine.Info(TargetDataLine.class, audioFormat);
    }

    // Start recording audio
    public void startRecording() {
        try {
            setupAudioFormat();
            targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
            targetDataLine.open(audioFormat);
            targetDataLine.start();

            isRecording = true;
            System.out.println("Recording started at " + recordingSession.getStartTimeFormatted() + "...");

            // Capture audio data and save it to a file (e.g., WAV format)
            Thread recordingThread = new Thread(() -> {
                try {
                    File audioFile = new File("recording_" + recordingSession.getStartTimeFormatted() + ".wav");
                    AudioInputStream audioStream = new AudioInputStream(targetDataLine);
                    AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, audioFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            recordingThread.start();

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Stop recording audio
    public void stopRecording() {
        if (targetDataLine != null && isRecording) {
            targetDataLine.stop();
            targetDataLine.close();
            isRecording = false;
            System.out.println("Recording stopped.");
        }
    }

    // Check if audio is currently being recorded
    public boolean isRecording() {
        return isRecording;
    }
}
