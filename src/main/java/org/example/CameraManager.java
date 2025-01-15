package org.example;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraManager {


    private VideoCapture camera;
    private VideoWriter videoWriter;
    private boolean isRecording = false;

    // Start recording
    public void startRecording() {
        camera = new VideoCapture("rtsp://your_camera_ip");
        if (!camera.isOpened()) {
            System.out.println("Camera connection failed");
            return;
        }

        // Get frame width, height, and FPS
        double frameWidth = camera.get(3); // CAP_PROP_FRAME_WIDTH
        double frameHeight = camera.get(4); // CAP_PROP_FRAME_HEIGHT
        double fps = camera.get(5); // CAP_PROP_FPS

        // Create a file name with timestamp
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        String fileName = "CCTV_Record_" + timestamp + ".avi";

        // Set up VideoWriter
        Size frameSize = new Size(frameWidth, frameHeight);
        int fourcc = VideoWriter.fourcc('M', 'J', 'P', 'G'); // Motion-JPEG codec
        videoWriter = new VideoWriter(fileName, fourcc, fps, frameSize, true);

        if (!videoWriter.isOpened()) {
            System.out.println("Failed to open VideoWriter");
            return;
        }

        isRecording = true;
        System.out.println("Recording started...");

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
