package com.careerconnect.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class FileStorageService {
    private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);
    private final Cloudinary cloudinary;

    public FileStorageService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        try {
            if (file == null || file.isEmpty()) {
                throw new IllegalArgumentException("File cannot be null or empty");
            }

            Map<String, Object> uploadOptions = ObjectUtils.asMap(
                "resource_type", "auto",
                "folder", "career_connect"
            );

            Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadOptions);
            String url = (String) uploadResult.get("secure_url");
            logger.info("File uploaded successfully: {}", url);
            return url;
        } catch (IOException e) {
            logger.error("Error uploading file: {}", e.getMessage());
            throw new IOException("Failed to upload file: " + e.getMessage());
        }
    }

    public void deleteFile(String publicId) throws IOException {
        try {
            if (publicId == null || publicId.isEmpty()) {
                throw new IllegalArgumentException("Public ID cannot be null or empty");
            }

            Map<?, ?> result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            logger.info("File deleted successfully: {}", result);
        } catch (IOException e) {
            logger.error("Error deleting file: {}", e.getMessage());
            throw new IOException("Failed to delete file: " + e.getMessage());
        }
    }
} 