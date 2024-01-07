package com.example.Backend.Controllers;

import com.example.Backend.Models.Annotation;
import com.example.Backend.Models.MultiAnnotation;
import com.example.Backend.Models.SingleAnnotation;
import com.example.Backend.Models.XML;
import com.example.Backend.Repositories.AnnotationRepository;
import com.example.Backend.Repositories.XMLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/annotations")
public class AnnotationController {
    private XMLRepository xmlRepo;

    private AnnotationRepository annoRepository;

    @Autowired
    public AnnotationController(XMLRepository xmlRepo, AnnotationRepository annoRepository) {
        this.xmlRepo = xmlRepo;
        this.annoRepository = annoRepository;
    }

    @PostMapping("/add")
    @CrossOrigin("*")
    public String addAnnotations(@RequestBody addAnnotation payload) {
        int xmlId = payload.xmlId;
        List<SingleAnnotation> singleAnnotations = payload.singleAnnotations;
        List<MultiAnnotation> multiAnnotations = payload.multiAnnotations;
        if (xmlRepo.findById(xmlId).isEmpty()) throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        XML xml = xmlRepo.findById(xmlId).get();
        if (payload.multiAnnotations != null) {
            for (int i = 0; i < multiAnnotations.size(); i++) {
                MultiAnnotation newAnno = multiAnnotations.get(i);
                newAnno.setXml(xml);
                multiAnnotations.set(i, newAnno);
                if (xml.hasAnnotationWithName(newAnno.getWord())) {
                    int oldAnno = xml.getAnnotationIndexByWord(newAnno.getWord());
                    xml.getAnnotations().set(oldAnno, newAnno);
                } else {
                    xml.getAnnotations().add(newAnno);
                }
                annoRepository.save(multiAnnotations.get(i));
            }
        }
        if (payload.singleAnnotations != null) {
            for (int i = 0; i < singleAnnotations.size(); i++) {
                SingleAnnotation newAnno = singleAnnotations.get(i);
                newAnno.setXml(xml);
                singleAnnotations.set(i, newAnno);
                if (xml.hasAnnotationsWithIndex(newAnno.getStartIndex(), newAnno.getEndIndex())) {
                    int oldAnno = xml.getAnnotationIndexByIndex(newAnno.getStartIndex(), newAnno.getEndIndex());
                    xml.getAnnotations().set(oldAnno, newAnno);
                } else {
                    xml.getAnnotations().add(newAnno);
                }
                annoRepository.save(singleAnnotations.get(i));
            }
        }
        xmlRepo.save(xml);
        return "saved";
    }

    @GetMapping("/byxml/{id}")
    public @ResponseBody List<Annotation> getById(@PathVariable int id) {
        if (xmlRepo.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        return xmlRepo.findById(id).get().getAnnotations();
    }

    @GetMapping("/byxml/{id}/by-word/{word}/**")
    @CrossOrigin("*")
    public @ResponseBody Annotation getByIdAndWord(@PathVariable int id, @PathVariable String word) {
        List<Annotation> annotations = this.getById(id);
        for (Annotation i : annotations) {
            if (i instanceof MultiAnnotation) {
                if (((MultiAnnotation) i).getWord().equals(word))
                    return i;
            }
        }
        throw new ResponseStatusException(HttpStatusCode.valueOf(404));
    }

    @GetMapping("byxml/{id}/by-index/{startindex}-{endindex}")
    @CrossOrigin("*")
    public @ResponseBody Annotation getByIdAndIndexes(@PathVariable int id, @PathVariable int startindex, @PathVariable int endindex) {
        List<Annotation> annotations = this.getById(id);
        for (Annotation i : annotations) {
            if (i instanceof SingleAnnotation) {
                if (((SingleAnnotation) i).getStartIndex() == startindex && ((SingleAnnotation) i).getEndIndex() == endindex)
                    return i;
            }
        }
        throw new ResponseStatusException(HttpStatusCode.valueOf(404));
    }

    @GetMapping("/")
    @CrossOrigin("*")
    public @ResponseBody Iterable<annotationWithForeignKey> get() {
        List<annotationWithForeignKey> annotationWithForeignKeys = new ArrayList<>();
        List<Annotation> annotations = (List<Annotation>) annoRepository.findAll();

        for (Annotation i : annotations) {
            annotationWithForeignKey temp = new annotationWithForeignKey();
            temp.xmlId = i.getXml().getId();
            temp.xmlName = i.getXml().getFilepath();
            temp.annotation = i;
            annotationWithForeignKeys.add(temp);
        }
        return annotationWithForeignKeys;
    }

    public static class annotationWithForeignKey {
        public int xmlId;
        public String xmlName;
        public Annotation annotation;
    }

    public static class addAnnotation {
        public int xmlId;
        public List<SingleAnnotation> singleAnnotations;
        public List<MultiAnnotation> multiAnnotations;
    }
}
