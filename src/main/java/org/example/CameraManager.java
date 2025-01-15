package org.example;

import org.opencv.videoio.VideoCapture;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraManager {


    private VideoCapture camera;

    public void startRecording() {
        camera = new VideoCapture("rtsp://your_camera_ip");
        if (!camera.isOpened()) {
            System.out.println("Camera connection failed");
            return;
        }

        // Create a file name with timestamp

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        String fileName = "CCTV_Record_" + timestamp + ".mp4";


    }

}
