package com.example.Backend.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class XML {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String filepath;

    @OneToMany
    private List<Annotation> annotations;

    public XML(String filepath) {
        this.filepath = filepath;
    }

    public int getId() {
        return id;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> annotations) {
        this.annotations = annotations;
    }

    public XML() {
    }

    public boolean hasAnnotationWithName(String word) {
        for (Annotation i : getAnnotations()) {
            if (i instanceof MultiAnnotation) {
                if (((MultiAnnotation) i).getWord().equals(word))
                    return true;
            }
        }
        return false;
    }

    public boolean hasAnnotationsWithIndex(int startIndex, int endIndex) {
        for (Annotation i : getAnnotations()) {
            if (i instanceof SingleAnnotation) {
                if (checkIndex((SingleAnnotation) i, startIndex, endIndex)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getAnnotationIndexByWord(String word) {
        for (int j = 0; j < annotations.size(); j++) {
            Annotation i = annotations.get(j);
            if (i instanceof MultiAnnotation multiAnnotation) {
                if (multiAnnotation.getWord().equals(word))
                    return j;
            }
        }
        return -1;
    }

    public int getAnnotationIndexByIndex(int startIndex, int endIndex) {
        List<Annotation> annotationList = getAnnotations();
        for (int j = 0; j < annotationList.size(); j++) {
            Annotation i = annotationList.get(j);
            if (i instanceof SingleAnnotation singleAnnotation) {
                if (checkIndex(singleAnnotation, startIndex, endIndex)) {
                    return j;
                }
            }
        }
        return -1;
    }

    private boolean checkIndex(SingleAnnotation singleAnnotation, int startIndex, int endIndex){
        return (singleAnnotation.getStartIndex() <= startIndex && singleAnnotation.getEndIndex() >= startIndex)
                || (singleAnnotation.getStartIndex() <= endIndex && singleAnnotation.getEndIndex() >= endIndex);
    }
}
