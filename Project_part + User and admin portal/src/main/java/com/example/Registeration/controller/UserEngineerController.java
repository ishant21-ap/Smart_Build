package com.example.Registeration.controller;

import com.example.Registeration.model.Engineers;
import com.example.Registeration.service.EngineerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/engineers")
public class UserEngineerController {

    @Autowired
    private EngineerService engineerService;


    @GetMapping("/getAllEngineers")
    public List<Engineers> getAllEngineers() {
        return engineerService.getAllEngineers();
    }


    @GetMapping("/experience/{years}")
    public List<Engineers> getEngineersByExperience(@PathVariable("years") int experience) {
        return engineerService.getEngineersByExperience(experience);
    }
}
