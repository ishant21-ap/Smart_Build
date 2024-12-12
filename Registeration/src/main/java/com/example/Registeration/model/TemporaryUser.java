package com.example.Registeration.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
public class TemporaryUser {

    @Id
    @NotBlank(message = "Email-id is required")
    @Email(message = "Invalid Email-id")
    @Indexed(unique = true)
    private String email;
    private String otp;

    public @NotBlank(message = "Email-id is required") @Email(message = "Invalid Email-id") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email-id is required") @Email(message = "Invalid Email-id") String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
