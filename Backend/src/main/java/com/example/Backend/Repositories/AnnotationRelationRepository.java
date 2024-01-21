package com.example.Backend.Repositories;

import com.example.Backend.Models.RelatedAnnotation;
import org.springframework.data.repository.CrudRepository;

public interface AnnotationRelationRepository extends CrudRepository<RelatedAnnotation, Integer> {
}
