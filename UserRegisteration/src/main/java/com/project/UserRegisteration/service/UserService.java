package com.project.UserRegisteration.service;

import com.project.UserRegisteration.model.User;
import com.project.UserRegisteration.repository.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private Random random = new Random();

    public String registerUser(User user) throws MessagingException {
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email already exists";
        }

        //Encoding the password
        user.setPassword(encoder.encode(user.getPassword()));

        String otp = String.format("%04d", random.nextInt(10000));
        user.setOtp(otp);
        user.setVerified(false);

        userRepository.save(user);
        emailService.sendOtp(user.getEmail(), otp);
        return "User registered successfully! Please verify your email.";
    }

    public String verifyOtp(String email, String otp){
        Optional<User> userOpt = userRepository.findByEmail(email);
        if(userOpt.isEmpty()) {
            return "Invalid Email";
        }

        User user = userOpt.get();
        if(user.getOtp().equals(otp)) {
            user.setVerified(true);
            user.setOtp(null);
            userRepository.save(user);
            return "Email verified successfully !";
        }
        else{
            return "Invalid OTP";
        }
    }

    public boolean authenticateUser(String email, String password) throws MessagingException {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if(userOpt.isPresent()){
            User user = userOpt.get();
            return encoder.matches(password, user.getPassword());
        }
        return false;
    }


    public ResponseEntity <String> forgotPassword(String email) throws MessagingException {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if(userOpt.isEmpty()){
            return ResponseEntity.status(404).body("Email not found");
        }
        User user = userOpt.get();
        String otp = String.format("%04d", random.nextInt(10000));
        user.setOtp(otp);
        userRepository.save(user);
        emailService.sendOtp(user.getEmail(), otp);
        return ResponseEntity.ok("OTP Sent to Email");
    }



    public ResponseEntity <String> resetPassword(String email, String otp, String newPassword) throws MessagingException {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if(userOpt.isEmpty()){
            return ResponseEntity.status(404).body("Email not found");
        }
        User user = userOpt.get();
        if(user.getOtp() != null && user.getOtp().equals(otp)){
            user.setPassword(encoder.encode(newPassword));
            user.setOtp(null);
            userRepository.save(user);
            return ResponseEntity.ok("Password reset successfully !");
        }
        else{
            return ResponseEntity.status(400).body("Invalid OTP");
        }
    }
}
