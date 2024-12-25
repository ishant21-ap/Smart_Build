package com.example.Registeration.controller;

import com.example.Registeration.model.UserProject;
import com.example.Registeration.service.UserProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class UserProjectController {

    @Autowired
    private UserProjectService userProjectService;


    @GetMapping("/email")
    public List<UserProject> getAllProjectsByEmail(@RequestParam("email") String email) {
        System.out.println(email);
        List<UserProject> userProjects = userProjectService.getAllProjectsByEmail(email);
        if(userProjects == null){
            return new ArrayList<UserProject>();
        }else {
            return userProjects;
        }
    }


    // Create a new project
    @PostMapping
    public ResponseEntity<?> createProject(@Valid @RequestBody UserProject userProject) {
        try {
            UserProject createdProject = userProjectService.createProject(userProject);
            return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the project.");
        }
    }

    // Get project by ID
    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
        try {
            UserProject project = userProjectService.getProjectById(projectId);
            return project != null
                    ? new ResponseEntity<>(project, HttpStatus.OK)
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching the project.");
        }
    }

    // Get all projects
    @GetMapping("/getAllProjects")
    public ResponseEntity<List<UserProject>> getAllProjects() {
        try {
            List<UserProject> projects = userProjectService.getAllProjects();
            return new ResponseEntity<>(projects, HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Update an existing project
    @PutMapping("/{projectId}")
    public ResponseEntity<?> updateProject(@PathVariable String projectId, @Valid @RequestBody UserProject userProject) {
        try {
            UserProject updatedProject = userProjectService.updateProject(projectId, userProject);
            return updatedProject != null
                    ? new ResponseEntity<>(updatedProject, HttpStatus.OK)
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found.");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the project.");
        }
    }

    // Delete a project
    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId) {
        try {
            boolean isDeleted = userProjectService.deleteProject(projectId);
            return isDeleted
                    ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the project.");
        }
    }

    // Download project as a PDF
    @GetMapping("/{projectId}/download-pdf")
    public ResponseEntity<?> downloadProjectPdf(@PathVariable String projectId) {
        try {
            byte[] pdfData = userProjectService.generateProjectPdf(projectId);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=project_" + projectId + ".pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfData);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while generating the PDF.");
        }
    }
}
