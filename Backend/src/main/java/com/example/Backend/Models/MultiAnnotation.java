package com.example.Backend.Models;

import jakarta.persistence.Entity;

@Entity
public class MultiAnnotation extends Annotation{
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
