package com.example.Registeration.repository;

import com.example.Registeration.model.Engineers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EngineerRepository extends MongoRepository<Engineers, String> {

    List<Engineers> findByYearsOfExperienceGreaterThanEqual(int years);
}
