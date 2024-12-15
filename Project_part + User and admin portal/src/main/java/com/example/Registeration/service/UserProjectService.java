package com.example.Registeration.service;

import com.example.Registeration.model.UserProject;
import com.example.Registeration.repository.UserProjectRepository;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

@Service
public class UserProjectService {

    @Autowired
    private UserProjectRepository userProjectRepository;


    public UserProject createProject(UserProject userProject) {
        return userProjectRepository.save(userProject);
    }


    public UserProject getProjectById(String projectId) {
        Optional<UserProject> project = userProjectRepository.findById(projectId);
        return project.orElse(null);
    }


    public List<UserProject> getAllProjects() {
        return userProjectRepository.findAll();
    }


    public UserProject updateProject(String projectId, UserProject userProject) {
        if (userProjectRepository.existsById(projectId)) {
            userProject.setProjectId(projectId);
            return userProjectRepository.save(userProject);
        }
        return null;
    }


    public boolean deleteProject(String projectId) {
        if (userProjectRepository.existsById(projectId)) {
            userProjectRepository.deleteById(projectId);
            return true;
        }
        return false;
    }



    public byte[] generateProjectpdf(String projectId) {
        Optional<UserProject> project = userProjectRepository.findById(projectId);
        if (project.isEmpty()) {
            throw new IllegalArgumentException(("Project not found with id: " + projectId));
        }
        UserProject userProject = project.get();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try{
            PdfWriter writer = new PdfWriter(baos);
            Document document = new Document(new com.itextpdf.kernel.pdf.PdfDocument(writer));

            // Add project details to PDF
            document.add(new Paragraph("Project Details"));
            document.add(new Paragraph("Project ID: " + project.get().getProjectId()));
            document.add(new Paragraph("Project Name: " + project.get().getProjectName()));
            document.add(new Paragraph("Progress: " + project.get().getProgress() + "%"));
            document.add(new Paragraph("Current Phase: " + project.get().getCurrentPhase()));

            if(userProject.getUpcomingPhases() != null && !userProject.getUpcomingPhases().isEmpty()) {
                document.add(new Paragraph("Upcoming Phases: "));
                for(String phase: userProject.getUpcomingPhases()) {
                    document.add(new Paragraph("- " + phase));
                }
            }


            if(userProject.getMaterials() != null && !userProject.getMaterials().isEmpty()) {
                document.add(new Paragraph("Materials: "));
                for(UserProject.Material material: userProject.getMaterials()) {
                    document.add(new Paragraph("Material id : " + material.getMaterialId()));
                    document.add(new Paragraph("Name : " + material.getName()));
                    document.add(new Paragraph(("Total Quantity : " + material.getTotalQuantity())));
                    document.add(new Paragraph("Remaining Quantity : " + material.getRemainingQuantity()));
                    document.add(new Paragraph("Total Cost : " + material.getTotalCost()));
                }
            }


            document.add(new Paragraph("Total Expenditure now : " + userProject.getTotalExpenditureNow()));
            document.add(new Paragraph("Estimated Expenditure : " + userProject.getEstimatedExpenditure()));
            document.add(new Paragraph("Cost Comparison : " + userProject.getCostComparison()));

            document.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
}
