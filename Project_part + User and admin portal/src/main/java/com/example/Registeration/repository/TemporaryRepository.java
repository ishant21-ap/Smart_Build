package com.example.Registeration.repository;

import com.example.Registeration.model.TemporaryUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemporaryRepository extends MongoRepository<TemporaryUser, String> {

}
