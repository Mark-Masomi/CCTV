# CCTV Application

A Java-based CCTV application designed to capture video from multiple IP cameras, including old smartphones connected to the same network. The application supports recording video with timestamp metadata, audio recording, and GPS location embedding. This project is intended to provide a flexible and cost-effective security solution.

## Features

- **Multiple Camera Support**: Connect and record from up to 32 IP cameras and smartphones acting as IP cameras.
- **Audio Recording**: Capture audio from the computer's microphone and sync it with video recordings.
- **Timestamped Video**: Automatically embed the exact date and time in the video file for accurate playback.
- **GPS Location Stamping**: Capture and store the GPS location when recording begins (optional).
- **Easy Upgrades**: The application is designed to allow easy addition of more cameras and new features in the future.
- **Local Storage**: Videos are saved to the local hard drive (future versions may support cloud integration).

## Requirements

- **Java 11 or higher**
- **Gradle 5.0 or higher** for building the project
- **OpenCV for Java** for video handling
- **Java Sound API** for audio recording

### Hardware Requirements

- **For IP Camera Support**: A stable local network with accessible IP cameras or smartphones configured as IP cameras.
- **For Audio Recording**: A working microphone on the host machine.

## Installation

### Prerequisites

1. **Install Java Development Kit (JDK) 11 or higher**:
   - Download JDK from [Oracle JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or use OpenJDK.

2. **Install Gradle**:
   - Follow the installation instructions from [Gradle Installation Guide](https://gradle.org/install/).

### Clone the Repository

Clone this repository to your local machine using the following command:

```bash
git clone https://github.com/Mark-Masomi/CCTV.git
cd CCTV
```

### Building the Project

1. Open a terminal in the project directory.
2. Build the project using Gradle:

```bash
gradle build
```

This will compile the source code and create a JAR file in the `build/libs` directory.

### Running the Application

1. After building the project, run the application with:

```bash
java -jar build/libs/cctv-application.jar
```

2. The application will start, and you can begin configuring your cameras and recording.

## Configuration

- **Adding Cameras**: Add the camera's video stream URL to the application by specifying the camera’s IP address and stream port (HTTP or RTSP).
  
  Example:
  ```java
  CameraManager cameraManager = new CameraManager();
  cameraManager.startRecording("http://192.168.1.100:8080/video"); // Replace with actual IP camera stream URL
  ```

- **Audio Settings**: The application will automatically capture audio from the computer's default microphone during recording. You can configure your microphone settings in the system’s sound settings.

- **GPS Location**: GPS location is captured at the time of recording start. Make sure the system has access to the location service or manually provide the location if no GPS hardware is available.

## Usage

Once the application is running, you can:

1. **Add Cameras**: Configure up to 32 IP cameras, including smartphones used as cameras.
2. **Start Recording**: Initiate recording with or without GPS location tagging.
3. **Playback**: Recorded videos will have the exact timestamp embedded, allowing you to review them with accurate time references.

## Future Features

- **Cloud Integration**: Optionally store recorded videos in cloud storage for easier access and backup.
- **Camera Control**: Add functionality to control camera settings remotely, such as zoom, focus, etc.
- **Multiple Video Formats**: Support for various video formats and compression methods.

## Contributing

We welcome contributions! To contribute:

1. Fork the repository.
2. Create a new branch for your feature or fix.
3. Commit your changes and push to your fork.
4. Create a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- **OpenCV** for handling video streams.
- **Java Sound API** for audio recording.
- **IP Webcam** and **DroidCam** for turning smartphones into IP cameras.
