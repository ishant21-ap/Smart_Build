package com.example.Registeration.repository;

import com.example.Registeration.model.AdminModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<AdminModel, String> {
}
