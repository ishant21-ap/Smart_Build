package com.example.Registeration.service;

import com.example.Registeration.model.Engineers;
import com.example.Registeration.repository.EngineerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineerService {

    @Autowired
    private EngineerRepository engineerRepository;


    public Engineers addEngineer(Engineers engineer){
        return engineerRepository.save(engineer);
    }


    public List<Engineers> getAllEngineers(){
        return engineerRepository.findAll();
    }


    public List<Engineers> getEngineersByExperience(int years){
        return engineerRepository.findByYearsOfExperienceGreaterThanEqual(years);
    }


    public void deleteEngineer(String id){
        engineerRepository.deleteById(id);
    }
}
