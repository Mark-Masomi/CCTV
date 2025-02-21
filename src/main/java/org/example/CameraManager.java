package org.example;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;

public class CameraManager {


    private VideoCapture camera;
    private VideoWriter videoWriter;
    private boolean isRecording = false;
    private RecordingSession recordingSession;

    public CameraManager(RecordingSession recordingSession) {
        this.recordingSession = recordingSession;
    }

    // Start recording from a local webcam (specified by webcamIndex)
    public void startLocalCamera(int webcamIndex) {
        camera = new VideoCapture(webcamIndex); // Local webcam index
        if (!camera.isOpened()) {
            System.out.println("Camera connection failed");
            return;
        }

        // Get frame width, height, and FPS
        double frameWidth = camera.get(3); // CAP_PROP_FRAME_WIDTH
        double frameHeight = camera.get(4); // CAP_PROP_FRAME_HEIGHT
        double fps = camera.get(5); // CAP_PROP_FPS

        // Use the fileName generated earlier for the video file
        String fileName = "CCTV_Record_" + recordingSession.getStartTimeFormatted() + ".avi";  // Safe filename format

        // Set up VideoWriter
        Size frameSize = new Size(frameWidth, frameHeight);
        int fourcc = VideoWriter.fourcc('M', 'J', 'P', 'G'); // Motion-JPEG codec
        videoWriter = new VideoWriter(fileName, fourcc, fps, frameSize, true);

        if (!videoWriter.isOpened()) {
            System.out.println("Failed to open VideoWriter");
            return;
        }

        isRecording = true;
        System.out.println("Recording started at " + recordingSession.getStartTimeFormatted() + "...");

        // Capture and write frames
        Mat frame = new Mat();
        while (isRecording) {
            if (camera.read(frame)) {
                videoWriter.write(frame);
            } else {
                System.out.println("Failed to capture frame");
                break;
            }
        }

        stopRecording();
    }

    // Start recording from an IP camera (rtspUrl)
    public void startIPCamera(String rtspUrl) {
        camera = new VideoCapture(rtspUrl); // Use the RTSP URL for IP camera
        if (!camera.isOpened()) {
            System.out.println("Camera connection failed");
            return;
        }

        // Get frame width, height, and FPS
        double frameWidth = camera.get(3); // CAP_PROP_FRAME_WIDTH
        double frameHeight = camera.get(4); // CAP_PROP_FRAME_HEIGHT
        double fps = camera.get(5); // CAP_PROP_FPS

        // Use the fileName generated earlier for the video file
        String fileName = "CCTV_Record_" + recordingSession.getStartTimeFormatted() + ".avi";  // Safe filename format

        // Set up VideoWriter
        Size frameSize = new Size(frameWidth, frameHeight);
        int fourcc = VideoWriter.fourcc('M', 'J', 'P', 'G'); // Motion-JPEG codec
        videoWriter = new VideoWriter(fileName, fourcc, fps, frameSize, true);

        if (!videoWriter.isOpened()) {
            System.out.println("Failed to open VideoWriter");
            return;
        }

        isRecording = true;
        System.out.println("Recording started at " + recordingSession.getStartTimeFormatted() + "...");

        // Capture and write frames
        Mat frame = new Mat();
        while (isRecording) {
            if (camera.read(frame)) {
                videoWriter.write(frame);
            } else {
                System.out.println("Failed to capture frame");
                break;
            }
        }

        stopRecording();
    }

    // Stop recording
    public void stopRecording() {
        if (isRecording) {
            isRecording = false;
            videoWriter.release();
            camera.release();
            System.out.println("Recording stopped.");
        }
    }

    // Check if recording
    public boolean isRecording() {
        return isRecording;
    }
}
