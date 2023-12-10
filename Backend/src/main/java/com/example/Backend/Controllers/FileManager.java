package com.example.Backend.Controllers;

import com.example.Backend.Models.XML;
import com.example.Backend.Repositories.XMLRepository;
import com.example.Backend.Services.Storage.StorageService;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/files")
public class FileManager {
    private final StorageService storageService;

    private XMLRepository repo;

    @Autowired
    public FileManager(StorageService storageService, XMLRepository repo) {
        this.storageService = storageService;
        this.repo=repo;
    }

    @PostMapping("/upload")
    @CrossOrigin("*")
    public String upload(@RequestParam("file") MultipartFile file) {
        storageService.store(file);
        repo.save(new XML(file.getOriginalFilename()));
        return "uploaded";
    }

    @GetMapping("/object/id/{id}")
    @CrossOrigin("*")
    public @ResponseBody XML getById(@PathVariable int id){
        if (repo.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        return repo.findById(id).get();
    }

    @GetMapping("/object/name/{name}")
    @CrossOrigin("*")
    public @ResponseBody XML getById(@PathVariable String name){
        if (repo.findByFilepath(name).isEmpty()) throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        return repo.findByFilepath(name).get();
    }
}
