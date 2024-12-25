package com.example.Registeration.repository;

import com.example.Registeration.model.UserProject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProjectRepository extends MongoRepository<UserProject, String> {
    // Custom query methods can go here if needed
    List<UserProject> findUserProjectsByEmail(String email);
}
