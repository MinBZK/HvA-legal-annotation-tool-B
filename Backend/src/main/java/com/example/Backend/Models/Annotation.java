package com.example.Backend.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.data.repository.cdi.Eager;

import java.util.List;

@Entity
public class Annotation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private XML xml;

    private String color;
    private String description;

    public Annotation(int id, String color, String description, AnnotationType type) {
        this.id=id;
        this.color = color;
        this.description = description;
        this.type = type;
    }

    @OneToMany(fetch = FetchType.LAZY)
    private List<RelatedAnnotation> relatedAnnotations;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Annotation(XML xml) {
        this.xml = xml;
    }

    public AnnotationType type;

    public AnnotationType getType() {
        return type;
    }

    public void setType(AnnotationType type) {
        this.type = type;
    }

    public Annotation() {
    }

    public int getId() {
        return id;
    }

    public XML getXml() {
        return xml;
    }

    public void setXml(XML xml) {
        this.xml = xml;
    }

    public List<RelatedAnnotation> getRelatedAnnotations() {
        return relatedAnnotations;
    }

    public void setRelatedAnnotations(List<RelatedAnnotation> relatedAnnotations) {
        this.relatedAnnotations = relatedAnnotations;
    }
}
