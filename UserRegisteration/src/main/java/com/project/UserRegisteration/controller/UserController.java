package com.project.UserRegisteration.controller;


import com.project.UserRegisteration.model.User;
import com.project.UserRegisteration.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public String registerUser(@RequestBody User user) throws MessagingException {
        return userService.registerUser(user);
    }

    @PostMapping("/verify")
    public String verifyOtp(@RequestParam String email, @RequestParam String otp) throws MessagingException {
        return userService.verifyOtp(email, otp);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) throws MessagingException {
        boolean isAuthenticated =userService.authenticateUser(user.getEmail(), user.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok("Login Successful ");
        }
        else{
            return ResponseEntity.status(401).body("Invalid Credentials");
        }
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) throws MessagingException {
        return userService.forgotPassword(email);
    }


    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String otp, @RequestParam String newPassword) throws MessagingException {
        return userService.resetPassword(email, otp, newPassword);
    }

}
