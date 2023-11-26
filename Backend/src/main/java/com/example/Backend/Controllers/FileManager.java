package com.example.Backend.Controllers;

import com.example.Backend.Services.Storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/files")
public class FileManager {
    private final StorageService storageService;

    @Autowired
    public FileManager(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    @CrossOrigin("*")
    public String upload(@RequestParam("file") MultipartFile file) {
        storageService.store(file);

        return "uploaded";
    }
}
