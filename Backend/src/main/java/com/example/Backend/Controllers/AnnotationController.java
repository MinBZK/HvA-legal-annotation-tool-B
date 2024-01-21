package com.example.Backend.Controllers;

import com.example.Backend.Models.*;
import com.example.Backend.Repositories.AnnotationRelationRepository;
import com.example.Backend.Repositories.AnnotationRepository;
import com.example.Backend.Repositories.XMLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/annotations")
public class AnnotationController {
    private XMLRepository xmlRepo;

    private AnnotationRepository annoRepository;


    private AnnotationRelationRepository relationRepository;

    @Autowired
    public AnnotationController(XMLRepository xmlRepo, AnnotationRepository annoRepository, AnnotationRelationRepository relationRepository) {
        this.xmlRepo = xmlRepo;
        this.annoRepository = annoRepository;
        this.relationRepository = relationRepository;
    }

    @PostMapping("/add")
    @CrossOrigin("*")
    public @ResponseBody Annotation addAnnotations(@RequestBody addAnnotation payload) {
        int xmlId = payload.xmlId;
        List<SingleAnnotation> singleAnnotations = payload.singleAnnotations;
        List<MultiAnnotation> multiAnnotations = payload.multiAnnotations;
        if (xmlRepo.findById(xmlId).isEmpty()) throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        XML xml = xmlRepo.findById(xmlId).get();
        Annotation lastAnno = null;
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
                lastAnno = annoRepository.save(multiAnnotations.get(i));
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
                lastAnno = annoRepository.save(singleAnnotations.get(i));
            }
        }
        xmlRepo.save(xml);
        return lastAnno;
    }

    @GetMapping("/byxml/{id}")
    @CrossOrigin("*")
    public @ResponseBody List<Annotation> getById(@PathVariable int id) {
        if (xmlRepo.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatusCode.valueOf(404));

        List<Annotation> temp = xmlRepo.findById(id).get().getAnnotations();

        for (Annotation i : temp) {
            List<RelatedAnnotation> tempNew = i.getRelatedAnnotations();
            for (RelatedAnnotation relatedAnnotation : tempNew) {
                Annotation tempAnno = new Annotation(relatedAnnotation.getAnnotation().getId(), relatedAnnotation.getAnnotation().getColor(), relatedAnnotation.getAnnotation().getDescription(), relatedAnnotation.getAnnotation().getType());
                relatedAnnotation.setAnnotation(tempAnno);
            }
            i.setRelatedAnnotations(tempNew);
        }

        return temp;
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
        List<Annotation> annotations = (List<Annotation>) annoRepository.findAll();

        return handeRelation(annotations);
    }

    private static List<annotationWithForeignKey> handeRelation(List<Annotation> annotations) {
        List<annotationWithForeignKey> annotationWithForeignKeys = new ArrayList<>();
        for (Annotation i : annotations) {
            annotationWithForeignKey temp = new annotationWithForeignKey();
            temp.xmlId = i.getXml().getId();
            temp.xmlName = i.getXml().getFilepath();
            temp.annotation = i;
            annotationWithForeignKeys.add(temp);
        }
        return annotationWithForeignKeys;
    }

    @PostMapping("/add-relation")
    @CrossOrigin("*")
    public @ResponseBody RelatedAnnotation addRelation(@RequestBody AnnotationRelation annotationRelation) {
        if (annoRepository.findById(annotationRelation.annotation1Id).isEmpty())
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        Annotation annotation1 = annoRepository.findById(annotationRelation.annotation1Id).get();
        if (annoRepository.findById(annotationRelation.annotation2Id).isEmpty())
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        Annotation annotation2 = annoRepository.findById(annotationRelation.annotation2Id).get();

        RelatedAnnotation annoRel1 = new RelatedAnnotation();
        annoRel1.setAnnotation(annotation2);
        annoRel1.setDescription(annotationRelation.description1To2);
        annotation1.getRelatedAnnotations().add(annoRel1);
        relationRepository.save(annoRel1);

        RelatedAnnotation annoRel2 = new RelatedAnnotation();
        annoRel2.setAnnotation(annotation1);
        annoRel2.setDescription(annotationRelation.description2To1);
        annotation2.getRelatedAnnotations().add(annoRel2);
        relationRepository.save(annoRel2);


        annoRepository.save(annotation1);
        annoRepository.save(annotation2);

        annoRel1.setAnnotation(null);

        return annoRel1;
    }

    @PostMapping("/add-single-relation")
    @CrossOrigin("*")
    public @ResponseBody RelatedAnnotation addRelation(@RequestBody SingleAnnotationRelation annotationRelation) {
        if (annoRepository.findById(annotationRelation.annotation1Id).isEmpty())
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        Annotation annotation = annoRepository.findById(annotationRelation.annotation1Id).get();
        if (annoRepository.findById(annotationRelation.annotation2Id).isEmpty())
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        Annotation annotation2 = annoRepository.findById(annotationRelation.annotation2Id).get();

        RelatedAnnotation annoRel = new RelatedAnnotation();
        annoRel.setAnnotation(annotation2);
        annoRel.setDescription(annotationRelation.description);
        annotation.getRelatedAnnotations().add(annoRel);
        relationRepository.save(annoRel);


        annoRepository.save(annotation);

        annoRel.setAnnotation(null);

        return annoRel;
    }

    public static class annotationWithForeignKey {
        public int xmlId;
        public String xmlName;
        public Annotation annotation;
    }

    public static class AnnotationRelation {
        public int annotation1Id;
        public int annotation2Id;
        public String description1To2;
        public String description2To1;
    }

    public static class SingleAnnotationRelation {
        public int annotation1Id;
        public int annotation2Id;
        public String description;
    }

    public static class addAnnotation {
        public int xmlId;
        public List<SingleAnnotation> singleAnnotations;
        public List<MultiAnnotation> multiAnnotations;
    }
}
