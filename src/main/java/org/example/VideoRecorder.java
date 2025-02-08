package org.example;


public class VideoRecorder {

    private CameraManager cameraManager;
    private AudioManager audioManager;
    private boolean isRecording = false;

    public VideoRecorder(CameraManager cameraManager, AudioManager audioManager) {
        this.cameraManager = cameraManager;
        this.audioManager = audioManager;
    }

    // Start recording video and audio
    public void startRecording() {
        if (isRecording) {
            System.out.println("Recording is already in progress.");
            return;
        }

        // Start audio recording
        audioManager.startRecording();

        // Start video recording
        cameraManager.startRecording();

        isRecording = true;
        System.out.println("Recording started...");
    }

    public void stopRecording() {
        if (!isRecording) {
            System.out.println("No recording in progress.");
            return;
        }

        // Stop video recording
        cameraManager.stopRecording();

        // Stop audio recording
        audioManager.stopRecording();

        isRecording = false;
        System.out.println("Recording stopped.");
    }
}
