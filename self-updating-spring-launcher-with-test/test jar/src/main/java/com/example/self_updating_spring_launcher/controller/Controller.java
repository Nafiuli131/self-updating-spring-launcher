package com.example.self_updating_spring_launcher.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class Controller {

    private static Process currentProcess;
    private static final String APP_JAR = "app.jar"; 

    @PostMapping("/upload")
public synchronized ResponseEntity<String> uploadJar(@RequestParam("file") MultipartFile file) throws Exception {
   
    Path jarPath = Paths.get(APP_JAR);
    Files.copy(file.getInputStream(), jarPath, StandardCopyOption.REPLACE_EXISTING);

    // 1️⃣ Stop the old process if it exists
    if (currentProcess != null && currentProcess.isAlive()) {
        System.out.println("Stopping current application...");
        currentProcess.destroy();
        currentProcess.waitFor(); // wait until port is free
    }

    // 2️⃣ Start the new JAR on the same port
    System.out.println("Starting new application on same port...");
    currentProcess = new ProcessBuilder("java", "-jar", APP_JAR, "--server.port=8080")
            .inheritIO()
            .start();

    return ResponseEntity.ok("Application updated and restarted on port 8080!");
}
    
}
