package com.example.Registeration.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Document
@AllArgsConstructor
@NoArgsConstructor
public class UserProject {


    @Id
    @NotNull(message = "Project ID cannot be null")
    private String projectId;

    @NotNull(message = "Project Name cannot be null")
    @Size(min = 1, message = "Project Name cannot be empty")
    private String projectName;

    @Positive(message = "Progress must be a positive value")
    @Min(value = 0, message = "Progress must be between 0 and 100")
    @Max(value = 100, message = "Progress must be between 0 and 100")
    private double progress;

    @NotNull(message = "Current Phase cannot be null")
    private String currentPhase;

    @Size(min = 1, message = "Upcoming phases cannot be empty")
    private List<String> upcomingPhases;


    private List<Material> materials;


    @NotNull(message = "Total Expenditure Now cannot be null")
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Total Expenditure Now must be a valid monetary value")
    private String totalExpenditureNow;

    @NotNull(message = "Estimated Expenditure cannot be null")
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Estimated Expenditure must be a valid monetary value")
    private String estimatedExpenditure;

    @NotNull(message = "Cost Comparison cannot be null")
    private String costComparison;

    // ManPower Model
    private Manpower manpower;

    // Scheduling Model
    private Collection<Scheduling> scheduling;

    // Project Images Model
    private Collection<Images> projectImages;


    public @NotNull(message = "Project ID cannot be null") String getProjectId() {
        return projectId;
    }

    public void setProjectId(@NotNull(message = "Project ID cannot be null") String projectId) {
        this.projectId = projectId;
    }

    public @NotNull(message = "Project Name cannot be null") @Size(min = 1, message = "Project Name cannot be empty") String getProjectName() {
        return projectName;
    }

    public void setProjectName(@NotNull(message = "Project Name cannot be null") @Size(min = 1, message = "Project Name cannot be empty") String projectName) {
        this.projectName = projectName;
    }

    @Positive(message = "Progress must be a positive value")
    @Min(value = 0, message = "Progress must be between 0 and 100")
    public double getProgress() {
        return progress;
    }

    public void setProgress(@Positive(message = "Progress must be a positive value") @Min(value = 0, message = "Progress must be between 0 and 100") double progress) {
        this.progress = progress;
    }

    public @NotNull(message = "Current Phase cannot be null") String getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(@NotNull(message = "Current Phase cannot be null") String currentPhase) {
        this.currentPhase = currentPhase;
    }

    public @Size(min = 1, message = "Upcoming phases cannot be empty") List<String> getUpcomingPhases() {
        return upcomingPhases;
    }

    public void setUpcomingPhases(@Size(min = 1, message = "Upcoming phases cannot be empty") List<String> upcomingPhases) {
        this.upcomingPhases = upcomingPhases;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public @NotNull(message = "Total Expenditure Now cannot be null") @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Total Expenditure Now must be a valid monetary value") String getTotalExpenditureNow() {
        return totalExpenditureNow;
    }

    public void setTotalExpenditureNow(@NotNull(message = "Total Expenditure Now cannot be null") @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Total Expenditure Now must be a valid monetary value") String totalExpenditureNow) {
        this.totalExpenditureNow = totalExpenditureNow;
    }

    public @NotNull(message = "Estimated Expenditure cannot be null") @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Estimated Expenditure must be a valid monetary value") String getEstimatedExpenditure() {
        return estimatedExpenditure;
    }

    public void setEstimatedExpenditure(@NotNull(message = "Estimated Expenditure cannot be null") @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Estimated Expenditure must be a valid monetary value") String estimatedExpenditure) {
        this.estimatedExpenditure = estimatedExpenditure;
    }

    public @NotNull(message = "Cost Comparison cannot be null") String getCostComparison() {
        return costComparison;
    }

    public void setCostComparison(@NotNull(message = "Cost Comparison cannot be null") String costComparison) {
        this.costComparison = costComparison;
    }

    public Manpower getManpower() {
        return manpower;
    }

    public void setManpower(Manpower manpower) {
        this.manpower = manpower;
    }

    public Collection<Scheduling> getScheduling() {
        return scheduling;
    }

    public void setScheduling(Collection<Scheduling> scheduling) {
        this.scheduling = scheduling;
    }

    public Collection<Images> getProjectImages() {
        return projectImages;
    }

    public void setProjectImages(Collection<Images> projectImages) {
        this.projectImages = projectImages;
    }




    @AllArgsConstructor
    @NoArgsConstructor
    public static class Material {
        @NotNull(message = "Material ID cannot be null")
        private String materialId;

        @NotNull(message = "Material Name cannot be null")
        @Size(min = 1, message = "Material Name cannot be empty")
        private String name;

        @Positive(message = "Total quantity must be positive")
        private double totalQuantity;

        @Positive(message = "Remaining quantity must be positive")
        private double remainingQuantity;

        @Positive(message = "Total cost must be positive")
        private double totalCost;

        public @NotNull(message = "Material ID cannot be null") String getMaterialId() {
            return materialId;
        }

        public void setMaterialId(@NotNull(message = "Material ID cannot be null") String materialId) {
            this.materialId = materialId;
        }

        public @NotNull(message = "Material Name cannot be null") @Size(min = 1, message = "Material Name cannot be empty") String getName() {
            return name;
        }

        public void setName(@NotNull(message = "Material Name cannot be null") @Size(min = 1, message = "Material Name cannot be empty") String name) {
            this.name = name;
        }

        @Positive(message = "Total quantity must be positive")
        public double getTotalQuantity() {
            return totalQuantity;
        }

        public void setTotalQuantity(@Positive(message = "Total quantity must be positive") double totalQuantity) {
            this.totalQuantity = totalQuantity;
        }

        @Positive(message = "Remaining quantity must be positive")
        public double getRemainingQuantity() {
            return remainingQuantity;
        }

        public void setRemainingQuantity(@Positive(message = "Remaining quantity must be positive") double remainingQuantity) {
            this.remainingQuantity = remainingQuantity;
        }

        @Positive(message = "Total cost must be positive")
        public double getTotalCost() {
            return totalCost;
        }

        public void setTotalCost(@Positive(message = "Total cost must be positive") double totalCost) {
            this.totalCost = totalCost;
        }
    }




    @AllArgsConstructor
    @NoArgsConstructor
    public static class Manpower {
        @Positive(message = "Total workers must be positive")
        private int totalWorkers;

        @NotNull(message = "Shift cannot be null")
        private String shift;

        @Positive(message = "Work hours must be positive")
        private int workHours;

        @NotNull(message = "From Time cannot be null")
        private LocalDateTime fromTime;

        @NotNull(message = "To Time cannot be null")
        private LocalDateTime toTime;


        @Positive(message = "Total workers must be positive")
        public int getTotalWorkers() {
            return totalWorkers;
        }

        public void setTotalWorkers(@Positive(message = "Total workers must be positive") int totalWorkers) {
            this.totalWorkers = totalWorkers;
        }

        public @NotNull(message = "Shift cannot be null") String getShift() {
            return shift;
        }

        public void setShift(@NotNull(message = "Shift cannot be null") String shift) {
            this.shift = shift;
        }

        @Positive(message = "Work hours must be positive")
        public int getWorkHours() {
            return workHours;
        }

        public void setWorkHours(@Positive(message = "Work hours must be positive") int workHours) {
            this.workHours = workHours;
        }

        public @NotNull(message = "From Time cannot be null") LocalDateTime getFromTime() {
            return fromTime;
        }

        public void setFromTime(@NotNull(message = "From Time cannot be null") LocalDateTime fromTime) {
            this.fromTime = fromTime;
        }

        public @NotNull(message = "To Time cannot be null") LocalDateTime getToTime() {
            return toTime;
        }

        public void setToTime(@NotNull(message = "To Time cannot be null") LocalDateTime toTime) {
            this.toTime = toTime;
        }
    }


    @AllArgsConstructor
    @NoArgsConstructor
    public static class Scheduling {
        private List<Milestone> milestones;


        public List<Milestone> getMilestones() {
            return milestones;
        }

        public void setMilestones(List<Milestone> milestones) {
            this.milestones = milestones;
        }
    }




    @AllArgsConstructor
    @NoArgsConstructor
    public static class Milestone {
        @NotNull(message = "Milestone Name cannot be null")
        private String name;

        @NotNull(message = "From Time cannot be null")
        private LocalDateTime fromTime;

        @NotNull(message = "To Time cannot be null")
        private LocalDateTime toTime;

        @Positive(message = "Total Time must be positive")
        private long totalTime;


        public @NotNull(message = "Milestone Name cannot be null") String getName() {
            return name;
        }

        public void setName(@NotNull(message = "Milestone Name cannot be null") String name) {
            this.name = name;
        }

        public @NotNull(message = "From Time cannot be null") LocalDateTime getFromTime() {
            return fromTime;
        }

        public void setFromTime(@NotNull(message = "From Time cannot be null") LocalDateTime fromTime) {
            this.fromTime = fromTime;
        }

        public @NotNull(message = "To Time cannot be null") LocalDateTime getToTime() {
            return toTime;
        }

        public void setToTime(@NotNull(message = "To Time cannot be null") LocalDateTime toTime) {
            this.toTime = toTime;
        }

        @Positive(message = "Total Time must be positive")
        public long getTotalTime() {
            return totalTime;
        }

        public void setTotalTime(@Positive(message = "Total Time must be positive") long totalTime) {
            this.totalTime = totalTime;
        }
    }



    @AllArgsConstructor
    @NoArgsConstructor
    public static class Images {
        @NotNull(message = "Image ID cannot be null")
        private String imageId;

        @NotNull(message = "Image URL cannot be null")
        @Size(min = 1, message = "Image URL cannot be empty")
        private String imageUrl;


        public @NotNull(message = "Image ID cannot be null") String getImageId() {
            return imageId;
        }

        public void setImageId(@NotNull(message = "Image ID cannot be null") String imageId) {
            this.imageId = imageId;
        }

        public @NotNull(message = "Image URL cannot be null") @Size(min = 1, message = "Image URL cannot be empty") String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(@NotNull(message = "Image URL cannot be null") @Size(min = 1, message = "Image URL cannot be empty") String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }

    public void validateProject() {
        if (progress < 0 || progress > 100) {
            throw new IllegalArgumentException("Progress must be between 0 and 100");
        }
        if (upcomingPhases == null || upcomingPhases.isEmpty()) {
            throw new IllegalArgumentException("Upcoming phases cannot be empty or null");
        }

        // Additional business logic validations
        if (Double.parseDouble(totalExpenditureNow) < 0) {
            throw new IllegalArgumentException("Total Expenditure Now cannot be negative");
        }
        if (Double.parseDouble(estimatedExpenditure) < 0) {
            throw new IllegalArgumentException("Estimated Expenditure cannot be negative");
        }

        // Validate other model-specific business rules
        if (materials != null) {
            for (Material material : materials) {
                if (material.getTotalQuantity() < material.getRemainingQuantity()) {
                    throw new IllegalArgumentException("Remaining quantity cannot be greater than total quantity for material: " + material.getName());
                }
            }
        }
    }
}
