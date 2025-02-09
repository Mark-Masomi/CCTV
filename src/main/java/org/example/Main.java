package org.example;

import org.opencv.core.Core;

public class Main {
    public static void main(String[] args) {
        RecordingSession recordingSession=new RecordingSession();
        CameraManager cameraManager = new CameraManager(recordingSession);
        AudioManager audioManager = new AudioManager(recordingSession);
        VideoRecorder videoRecorder = new VideoRecorder(cameraManager, audioManager);

//
        System.setProperty("java.library.path", "C:/Users/moinm/IdeaProjects/CCTV/src/main/resources/native/opencv_java4100.dll");
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);  // This loads the OpenCV library

        // Now you can use OpenCV classes
        System.out.println("OpenCV version: " + Core.getVersionString());
//

        // Initialize the GUI
        RecordingGUI recordingGUI = new RecordingGUI();
        recordingGUI.setVisible(true);


        // Start recording
        videoRecorder.startRecording();

        // Perform recording operations...

        // Stop recording
        videoRecorder.stopRecording();


//        try {
//            // Create an HttpClient instance
//            HttpClient client = HttpClient.newHttpClient();
//
//            String apiKey = "AIzaSyAG5f3fb2p0ZoOP2J41KGkIziKSBDDq6gE";
//            String url = "https://www.googleapis.com/geolocation/v1/geolocate?key=" + apiKey;
//
//            // Create the API request to Google Geolocation API
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(new URI(url))
//                    .POST(HttpRequest.BodyPublishers.ofString("{}"))
//                    .header("Content-Type", "application/json")
//                    .build();
//
//            // Send request and get response
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//            // Print the raw response for debugging
//            System.out.println("Raw response: " + response.body());
//
//            JSONObject json = new JSONObject(response.body());
//
//            // Check if the response contains an error
//            if (json.has("error")) {
//                JSONObject error = json.getJSONObject("error");
//                System.out.println("Error code: " + error.getInt("code"));
//                System.out.println("Error message: " + error.getString("message"));
//            } else if (json.has("location")) {
//                JSONObject location = json.getJSONObject("location");
//                double latitude = location.getDouble("lat");
//                double longitude = location.getDouble("lng");
//                double accuracy = json.getDouble("accuracy");
//
//                System.out.println("Latitude: " + latitude);
//                System.out.println("Longitude: " + longitude);
//                System.out.println("Accuracy: " + accuracy + " meters");
//            } else {
//                System.out.println("Unexpected response format");
//            }
//
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//            e.printStackTrace();
//        }
    }
}