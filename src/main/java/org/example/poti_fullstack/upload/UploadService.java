package org.example.poti_fullstack.upload;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadService {
    List<String> upload(List<MultipartFile> fileList);
}