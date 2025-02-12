package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecordingGUI extends JFrame {
    private JButton startButton;
    private JButton stopButton;
    private JLabel statusLabel;
    private JTextArea logArea;
    private VideoRecorder videoRecorder;
    private JTextField ipCameraField;
    private JComboBox<String> webcamComboBox;

    public RecordingGUI() {
        // Initialize components
        startButton = new JButton("Start Recording");
        stopButton = new JButton("Stop Recording");
        statusLabel = new JLabel("Status: Idle");
        logArea = new JTextArea(10, 30);
        logArea.setEditable(false);
        ipCameraField = new JTextField("Enter IP Camera URL", 20);
        webcamComboBox = new JComboBox<>();

        // Set up the layout
        setLayout(new BorderLayout());
        JPanel controlPanel = new JPanel();
        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        controlPanel.add(ipCameraField);
        controlPanel.add(webcamComboBox);
        add(controlPanel, BorderLayout.NORTH);
        add(statusLabel, BorderLayout.CENTER);
        add(new JScrollPane(logArea), BorderLayout.SOUTH);

        // Initialize the VideoRecorder
        RecordingSession recordingSession = new RecordingSession();
        CameraManager cameraManager = new CameraManager(recordingSession);
        AudioManager audioManager = new AudioManager(recordingSession);
        videoRecorder = new VideoRecorder(cameraManager, audioManager);

        // Populate the JComboBox with available webcams
        String[] webcams = getAvailableWebcams();
        for (String webcam : webcams) {
            webcamComboBox.addItem(webcam);
        }

        // Add action listeners
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startRecording();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopRecording();
            }
        });

        // Set up the frame
        setTitle("Video and Audio Recorder");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private String[] getAvailableWebcams() {
        // Example logic to populate with webcam indices
        String[] webcams = new String[5]; // Adjust as needed
        for (int i = 0; i < 5; i++) {
            webcams[i] = "Webcam " + i;
        }
        return webcams;
    }

    private void startRecording() {
        statusLabel.setText("Status: Recording...");
        logArea.append("Recording started...\n");

        String ipCameraUrl = ipCameraField.getText();
        String selectedCamera = (String) webcamComboBox.getSelectedItem();

        if (ipCameraUrl != null && !ipCameraUrl.isEmpty()) {
            videoRecorder.startRecordingFromIP(ipCameraUrl); // Start IP camera
        } else if (selectedCamera != null && !selectedCamera.isEmpty()) {
            int webcamIndex = Integer.parseInt(selectedCamera.replaceAll("[^0-9]", ""));
            videoRecorder.startRecordingFromLocal(webcamIndex); // Start local webcam
        }
    }

    private void stopRecording() {
        statusLabel.setText("Status: Idle");
        logArea.append("Recording stopped.\n");
        videoRecorder.stopRecording();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RecordingGUI().setVisible(true);
            }
        });
    }
}
