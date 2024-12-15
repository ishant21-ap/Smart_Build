package com.example.Registeration.repository;

import com.example.Registeration.model.UserProject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProjectRepository extends MongoRepository<UserProject, String> {
    // Custom query methods can go here if needed
}
