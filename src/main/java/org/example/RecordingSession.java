package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordingSession {
    private long startTimeMillis;
    private String startTimeFormatted;

    public RecordingSession() {
        // Capture the current system time in milliseconds
        this.startTimeMillis = System.currentTimeMillis();

        // Format the start time for display purposes
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        this.startTimeFormatted = sdf.format(new Date(startTimeMillis));

        // Generate a valid filename
        String timestamp = sdf.format(new Date());
        String fileName = "recording_" + timestamp + ".wav";  // Safe filename format

    }

    public long getStartTimeMillis() {
        return startTimeMillis;
    }

    public String getStartTimeFormatted() {
        return startTimeFormatted;
    }
}
