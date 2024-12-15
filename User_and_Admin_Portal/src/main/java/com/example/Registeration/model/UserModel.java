package com.example.Registeration.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {



    @Id
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email-id")
    @Indexed(unique = true)
    private String email;


    @NotBlank(message = "Name is Required")
    private String name;

    private String otp;

    @NotBlank(message = "Passowrd is required")
    private String password;


    public @NotBlank(message = "Name is Required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is Required") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Invalid Email-id") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "Invalid Email-id") String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public @NotBlank(message = "Passowrd is required") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Passowrd is required") String password) {
        this.password = password;
    }
}
