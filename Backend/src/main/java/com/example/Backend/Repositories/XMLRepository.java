package com.example.Backend.Repositories;

import com.example.Backend.Models.XML;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface XMLRepository extends CrudRepository<XML, Integer> {
    Optional<XML> findByFilepath(String name);
}
