package com.example.Backend.Controllers;

import org.springframework.web.bind.annotation.RequestParam;
import com.example.Backend.Services.Storage.FileSystemStorageService;
import com.example.Backend.Services.Storage.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class XmlController {

    @Autowired
    private StorageProperties storageProperties;

    @Autowired
    private FileSystemStorageService storageService;

    @GetMapping("/get-xml")
    @CrossOrigin("*")
    public ResponseEntity<Resource> getXmlFile(@RequestParam String fileName) {
        try {
            Path path = Paths.get(storageProperties.getLocation()).resolve(fileName);
            Resource resource = new UrlResource(path.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok().body(resource);
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @GetMapping("/get-xmls")
    @CrossOrigin("*")
    public ResponseEntity<List<String>> getXmlFiles() {
        List<String> fileNames = storageService.getAllFileNames();
        return ResponseEntity.ok().body(fileNames);
    }
}

