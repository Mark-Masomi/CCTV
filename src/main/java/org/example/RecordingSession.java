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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.startTimeFormatted = sdf.format(new Date(startTimeMillis));
    }

    public long getStartTimeMillis() {
        return startTimeMillis;
    }

    public String getStartTimeFormatted() {
        return startTimeFormatted;
    }
}
