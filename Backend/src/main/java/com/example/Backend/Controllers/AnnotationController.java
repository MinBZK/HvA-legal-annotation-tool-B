package com.example.Backend.Controllers;

import com.example.Backend.Models.MultiAnnotation;
import com.example.Backend.Models.SingleAnnotation;
import com.example.Backend.Models.XML;
import com.example.Backend.Repositories.AnnotationRepository;
import com.example.Backend.Repositories.XMLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    public static class addAnnotation {
        public int xmlId;
        public List<SingleAnnotation> singleAnnotations;
        public List<MultiAnnotation> multiAnnotations;
    }
}
