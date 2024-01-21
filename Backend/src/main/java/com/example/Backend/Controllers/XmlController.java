package com.example.Backend.Controllers;

import com.example.Backend.Repositories.XMLRepository;
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

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class XmlController {

    @Autowired
    private StorageProperties storageProperties;

    @Autowired
    private FileSystemStorageService storageService;

    @Autowired
    private XMLRepository xmlRepo;

    @GetMapping("/get-xml")
    @CrossOrigin("*")
    public ResponseEntity<ResourceAndId> getXmlFile(@RequestParam String fileName) {
        try {
            Path path = Paths.get(storageProperties.getLocation()).resolve(fileName);
            Resource resource = new UrlResource(path.toUri());

            if ((resource.exists() || resource.isReadable())) {
                ResourceAndId returnVal = new ResourceAndId();
                returnVal.id = xmlRepo.findByFilepath(fileName).isPresent() ? xmlRepo.findByFilepath(fileName).get().getId() : 1; //Return one for debug purposes
                returnVal.text = resource.getContentAsString(StandardCharsets.UTF_8);
                return ResponseEntity.ok().body(returnVal);
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class ResourceAndId {
        public int id;
        public String text;
    }

    /**
     * Handles HTTP GET requests to retrieve a list of XML file names.
     *
     * @return A ResponseEntity containing the list of XML file names.
     */

    @GetMapping("/get-xmls")
    @CrossOrigin("*")
    public ResponseEntity<List<String>> getXmlFiles() {
        List<String> fileNames = storageService.getAllFileNames();
        return ResponseEntity.ok().body(fileNames);
    }
}
