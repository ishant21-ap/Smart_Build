package com.example.Registeration.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
public class AdminModel {

    @Id
    @NotBlank(message = "Email-id is required")
    @Email(message = "Invalid Email-id")
    @Indexed(unique = true)
    private String email;

    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "phone number is required")
    private String phone;

    public @NotBlank(message = "Email-id is required") @Email(message = "Invalid Email-id") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email-id is required") @Email(message = "Invalid Email-id") String email) {
        this.email = email;
    }

    public @NotBlank(message = "password is required") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "password is required") String password) {
        this.password = password;
    }

    public @NotBlank(message = "name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "name is required") String name) {
        this.name = name;
    }

    public @NotBlank(message = "phone number is required") String getPhone() {
        return phone;
    }

    public void setPhone(@NotBlank(message = "phone number is required") String phone) {
        this.phone = phone;
    }
}
