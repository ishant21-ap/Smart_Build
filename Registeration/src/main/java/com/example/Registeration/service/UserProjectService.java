package com.example.Registeration.service;

import com.example.Registeration.model.UserProject;
import com.example.Registeration.repository.UserProjectRepository;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

@Service
public class UserProjectService {

    @Autowired
    private UserProjectRepository userProjectRepository;

    // Helper method to validate UserProject data
    private void validateUserProject(UserProject userProject) {
        if (!StringUtils.hasText(userProject.getProjectName())) {
            throw new IllegalArgumentException("Project name cannot be null or empty");
        }

        if (userProject.getProgress() < 0 || userProject.getProgress() > 100) {
            throw new IllegalArgumentException("Progress must be between 0 and 100");
        }

        if (userProject.getCurrentPhase() == null) {
            throw new IllegalArgumentException("Current phase cannot be null");
        }

        if (userProject.getEstimatedExpenditure() != null && userProject.getTotalExpenditureNow() != null) {
            if (Double.parseDouble(userProject.getTotalExpenditureNow()) > Double.parseDouble(userProject.getEstimatedExpenditure())) {
                throw new IllegalArgumentException("Total expenditure now cannot exceed estimated expenditure");
            }
        }
    }


    public List<UserProject> getAllProjectsByEmail(String email) {
        return userProjectRepository.findUserProjectsByEmail(email);
    }


    public UserProject createProject(UserProject userProject) {
        validateUserProject(userProject);
        if(userProject.getEmail() == null || userProject.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        return userProjectRepository.save(userProject);
    }

    public UserProject getProjectById(String projectId) {
        Optional<UserProject> project = userProjectRepository.findById(projectId);
        return project.orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));
    }

    public List<UserProject> getAllProjects() {
        return userProjectRepository.findAll();
    }

    public UserProject updateProject(String projectId, UserProject userProject) {
        if (!userProjectRepository.existsById(projectId)) {
            throw new IllegalArgumentException("Project not found with id: " + projectId);
        }

        userProject.setProjectId(projectId);
        validateUserProject(userProject);
        return userProjectRepository.save(userProject);
    }

    public boolean deleteProject(String projectId) {
        if (!userProjectRepository.existsById(projectId)) {
            throw new IllegalArgumentException("Project not found with id: " + projectId);
        }
        userProjectRepository.deleteById(projectId);
        return true;
    }



    public byte[] generateProjectPdf(String projectId) {
        UserProject userProject = getProjectById(projectId);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(baos)) {
            Document document = new Document(new com.itextpdf.kernel.pdf.PdfDocument(writer));

            // Add project details to PDF
            document.add(new Paragraph("Project Details"));
            document.add(new Paragraph("Project ID: " + userProject.getProjectId()));
            document.add(new Paragraph("Project Name: " + userProject.getProjectName()));
            document.add(new Paragraph("Progress: " + userProject.getProgress() + "%"));
            document.add(new Paragraph("Current Phase: " + userProject.getCurrentPhase()));

            if (userProject.getUpcomingPhases() != null && !userProject.getUpcomingPhases().isEmpty()) {
                document.add(new Paragraph("Upcoming Phases: "));
                for (String phase : userProject.getUpcomingPhases()) {
                    document.add(new Paragraph("- " + phase));
                }
            }

            if (userProject.getMaterials() != null && !userProject.getMaterials().isEmpty()) {
                document.add(new Paragraph("Materials: "));
                for (UserProject.Material material : userProject.getMaterials()) {
                    document.add(new Paragraph("Material ID: " + material.getMaterialId()));
                    document.add(new Paragraph("Name: " + material.getName()));
                    document.add(new Paragraph("Total Quantity: " + material.getTotalQuantity()));
                    document.add(new Paragraph("Remaining Quantity: " + material.getRemainingQuantity()));
                    document.add(new Paragraph("Total Cost: " + material.getTotalCost()));
                }
            }

            document.add(new Paragraph("Total Expenditure Now: " + userProject.getTotalExpenditureNow()));
            document.add(new Paragraph("Estimated Expenditure: " + userProject.getEstimatedExpenditure()));
            document.add(new Paragraph("Cost Comparison: " + userProject.getCostComparison()));

            document.close();

        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF: " + e.getMessage(), e);
        }

        return baos.toByteArray();
    }
}
