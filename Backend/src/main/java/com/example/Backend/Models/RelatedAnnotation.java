package com.example.Backend.Models;

import jakarta.persistence.*;

@Entity
public class RelatedAnnotation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    private String description;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Annotation annotation;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Annotation getAnnotation() {
        return annotation;
    }



    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }
}
