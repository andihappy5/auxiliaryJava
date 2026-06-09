package com.happy.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.*;
import java.util.*;

@RestController
@RequestMapping("/api/files")
public class FileUploadDownloadController {

    private static final String UPLOAD_DIR = System.getProperty("user.home") + "/auxiliary_uploads";

    public FileUploadDownloadController() {
        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
        } catch (IOException e) {
            throw new RuntimeException("Failed to create upload directory", e);
        }
    }

    @PostMapping("/upload")
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (file.isEmpty()) {
                response.put("success", false);
                response.put("message", "File is empty");
                return response;
            }

            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR, fileName);
            Files.write(filePath, file.getBytes());

            response.put("success", true);
            response.put("message", "File uploaded successfully");
            response.put("fileName", fileName);
            response.put("size", file.getSize());
        } catch (IOException e) {
            response.put("success", false);
            response.put("message", "Upload failed: " + e.getMessage());
        }
        return response;
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR, fileName);
            if (!Files.exists(filePath)) {
                return ResponseEntity.notFound().build();
            }

            byte[] fileContent = Files.readAllBytes(filePath);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(fileContent);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/list")
    public Map<String, Object> listFiles() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Map<String, String>> files = new ArrayList<>();
            File[] fileList = new File(UPLOAD_DIR).listFiles();

            if (fileList != null) {
                for (File file : fileList) {
                    if (file.isFile()) {
                        Map<String, String> fileInfo = new HashMap<>();
                        fileInfo.put("name", file.getName());
                        fileInfo.put("size", String.valueOf(file.length()));
                        fileInfo.put("modified", String.valueOf(file.lastModified()));
                        files.add(fileInfo);
                    }
                }
            }

            response.put("success", true);
            response.put("files", files);
            response.put("count", files.size());
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to list files: " + e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/delete/{fileName}")
    public Map<String, Object> deleteFile(@PathVariable String fileName) {
        Map<String, Object> response = new HashMap<>();
        try {
            Path filePath = Paths.get(UPLOAD_DIR, fileName);
            if (!Files.exists(filePath)) {
                response.put("success", false);
                response.put("message", "File not found");
                return response;
            }

            Files.delete(filePath);
            response.put("success", true);
            response.put("message", "File deleted successfully");
        } catch (IOException e) {
            response.put("success", false);
            response.put("message", "Delete failed: " + e.getMessage());
        }
        return response;
    }
}
