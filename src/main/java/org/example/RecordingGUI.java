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

    public RecordingGUI() {
        // Initialize components
        startButton = new JButton("Start Recording");
        stopButton = new JButton("Stop Recording");
        statusLabel = new JLabel("Status: Idle");
        logArea = new JTextArea(10, 30);
        logArea.setEditable(false);

        // Set up the layout
        setLayout(new BorderLayout());
        JPanel controlPanel = new JPanel();
        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        add(controlPanel, BorderLayout.NORTH);
        add(statusLabel, BorderLayout.CENTER);
        add(new JScrollPane(logArea), BorderLayout.SOUTH);

        // Initialize the VideoRecorder
        RecordingSession recordingSession = new RecordingSession();
        CameraManager cameraManager = new CameraManager(recordingSession);
        AudioManager audioManager = new AudioManager(recordingSession);
        videoRecorder = new VideoRecorder(cameraManager, audioManager);

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

    private void startRecording() {
        statusLabel.setText("Status: Recording...");
        logArea.append("Recording started...\n");
        videoRecorder.startRecording();
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
