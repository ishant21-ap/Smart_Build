package com.example.Registeration.controller;

import com.example.Registeration.model.UserProject;
import com.example.Registeration.service.UserProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class UserProjectController {

    @Autowired
    private UserProjectService userProjectService;


    @PostMapping
    public ResponseEntity<UserProject> createProject(@Valid @RequestBody UserProject userProject) {
        UserProject createdProject = userProjectService.createProject(userProject);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }


    @GetMapping("/{projectId}")
    public ResponseEntity<UserProject> getProjectById(@PathVariable String projectId) {
        UserProject project = userProjectService.getProjectById(projectId);
        return project != null ? new ResponseEntity<>(project, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping
    public ResponseEntity<List<UserProject>> getAllProjects() {
        List<UserProject> projects = userProjectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }


    @PutMapping("/{projectId}")
    public ResponseEntity<UserProject> updateProject(@PathVariable String projectId, @Valid @RequestBody UserProject userProject) {
        UserProject updatedProject = userProjectService.updateProject(projectId, userProject);
        return updatedProject != null ? new ResponseEntity<>(updatedProject, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable String projectId) {
        boolean isDeleted = userProjectService.deleteProject(projectId);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/{projectId}/download-pdf")
    public ResponseEntity<byte[]> downloadProjectPdf(@PathVariable String projectId) {
        byte[] pdfData = userProjectService.generateProjectpdf(projectId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=project_" + projectId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfData);
    }
}
