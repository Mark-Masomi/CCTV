package org.example;


public class VideoRecorder {

    private CameraManager cameraManager;
    private AudioManager audioManager;
    private boolean isRecording = false;

    public VideoRecorder(CameraManager cameraManager, AudioManager audioManager) {
        this.cameraManager = cameraManager;
        this.audioManager = audioManager;
    }

    // Start recording video and audio (default behavior)
    public void startRecording() {
        if (isRecording) {
            System.out.println("Recording is already in progress.");
            return;
        }

        // Start audio recording
        audioManager.startRecording();

        // Start video recording (local camera by default)
        cameraManager.startLocalCamera(0); // Or any webcam index you want to use

        isRecording = true;
        System.out.println("Recording started...");
    }

    // Start recording from an IP camera
    public void startRecordingFromIP(String rtspUrl) {
        if (isRecording) {
            System.out.println("Recording is already in progress.");
            return;
        }

        // Start audio recording
        audioManager.startRecording();

        // Start video recording from IP camera
        cameraManager.startIPCamera(rtspUrl);

        isRecording = true;
        System.out.println("Recording started from IP camera...");
    }

    // Start recording from a local webcam
    public void startRecordingFromLocal(int webcamIndex) {
        if (isRecording) {
            System.out.println("Recording is already in progress.");
            return;
        }

        // Start audio recording
        audioManager.startRecording();

        // Start video recording from local webcam
        cameraManager.startLocalCamera(webcamIndex);

        isRecording = true;
        System.out.println("Recording started from local webcam...");
    }

    // Stop recording
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
