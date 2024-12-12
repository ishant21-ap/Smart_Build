package com.example.Registeration.controller;

import com.example.Registeration.model.Engineers;
import com.example.Registeration.service.EngineerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/engineers")
public class AdminEngineerController {

    @Autowired
    private EngineerService engineerService;


    @PostMapping("/addEngineer")
    public Engineers addEngineer(@RequestBody Engineers engineer){
        return engineerService.addEngineer(engineer);
    }



    @PutMapping("/updateEngineer/{id}")
    public Engineers updateEngineer(@PathVariable String id, @RequestBody Engineers engineer){
        engineer.setId(id);
        return engineerService.addEngineer(engineer);
    }



    @DeleteMapping("/deleteEngineer/{id}")
    public void deleteEngineer(@PathVariable String id){
        engineerService.deleteEngineer(id);
    }
}
