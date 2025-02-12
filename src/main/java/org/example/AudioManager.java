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

    // Set up the audio format with 16000 Hz, mono, 16 bit sample size, and PCM_SIGNED encoding
    private void setupAudioFormat() {
        try {
            audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    16000.0F,  // Sample rate to 16000 Hz
                    16,        // Sample size in bits
                    1,         // Mono channel
                    2,         // Frame size
                    16000.0F,  // Frame rate
                    false);    // Little-endian

            info = new DataLine.Info(TargetDataLine.class, audioFormat);

            // Check if the line is supported before opening it
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Audio line is not supported.");
                return;
            }

            targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
            targetDataLine.open(audioFormat);
            targetDataLine.start();

            isRecording = true;
            System.out.println("Recording started at " + recordingSession.getStartTimeFormatted() + "...");

            // Capture audio data and save it to a file (e.g., WAV format)
            Thread recordingThread = new Thread(() -> {
                try {
                    // Use the generated file name for the audio file
                    String fileName = "recording_" + recordingSession.getStartTimeFormatted() + ".wav";  // Safe filename format
                    File audioFile = new File(fileName);  // Use fileName here
                    AudioInputStream audioStream = new AudioInputStream(targetDataLine);
                    AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, audioFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            recordingThread.start();

        } catch (LineUnavailableException e) {
            System.err.println("Error: Audio line is unavailable.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error occurred.");
            e.printStackTrace();
        }
    }

    // Start recording audio
    public void startRecording() {
        // Print supported audio formats for debugging
        printSupportedAudioFormats();

        try {
            setupAudioFormat();
        } catch (Exception e) {
            System.err.println("Error starting audio recording: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to print supported audio formats from available mixers
    private void printSupportedAudioFormats() {
        try {
            // Get the available mixers
            Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();

            for (Mixer.Info mixerInfo : mixerInfos) {
                Mixer mixer = AudioSystem.getMixer(mixerInfo);

                // Get the line info for TargetDataLine (audio input)
                Line.Info[] lineInfos = mixer.getTargetLineInfo();

                for (Line.Info lineInfo : lineInfos) {
                    if (lineInfo instanceof DataLine.Info) {
                        DataLine.Info dataLineInfo = (DataLine.Info) lineInfo;

                        // Print supported formats
                        AudioFormat[] supportedFormats = dataLineInfo.getFormats();
                        for (AudioFormat format : supportedFormats) {
                            System.out.println("Supported Format: " + format.toString());
                        }
                    }
                }
            }
        } catch (Exception e) {
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
