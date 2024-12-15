package com.example.Registeration.service;

import com.example.Registeration.model.TemporaryUser;
import com.example.Registeration.model.UserModel;
import com.example.Registeration.repository.TemporaryRepository;
import com.example.Registeration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TemporaryRepository temporaryRepository;

    @Autowired
    private EmailService emailService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private Random random = new Random();


    //Generating and sending otp to email and also saving the email and otp in database
    public boolean generateOtpAndSendEmail(TemporaryUser temporaryUser) {
        String otp = String.format("%04d", random.nextInt(10000));
        temporaryUser.setOtp(otp);
        temporaryRepository.save(temporaryUser);
        return emailService.sendOtp(temporaryUser.getEmail(), otp);
    }


    public boolean verifyOtp(TemporaryUser temporaryUser) {
        Optional<TemporaryUser> existingUser = temporaryRepository.findById(temporaryUser.getEmail());
        if (existingUser.isPresent() && existingUser.get().getOtp().equals(temporaryUser.getOtp())) {
            temporaryRepository.deleteById(temporaryUser.getEmail());
            return true;
        }
        return false;
    }


    public boolean registerUser(UserModel user) {
        if (userRepository.findById(user.getEmail()).isPresent()) {
            return false;    //User already exists
        }

        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }


    //forgot-password and generate otp
    public boolean forgotPassword(String email) {
        if (userRepository.findById(email).isEmpty()) {
            return false;
        }
        String otp = String.format("%04d", random.nextInt(10000));
        TemporaryUser temporaryUser = new TemporaryUser();
        temporaryUser.setEmail(email);
        temporaryUser.setOtp(otp);
        temporaryRepository.save(temporaryUser);
        return emailService.sendOtp(email, otp);
    }


    //Reset the password after otp verification
    public boolean resetPassword(String email, String newPassword) {
        Optional<UserModel> user = userRepository.findById(email);
        if (user.isPresent()) {
            user.get().setPassword(encoder.encode(newPassword));
            userRepository.save(user.get());
            temporaryRepository.deleteById(email);
            return true;
        }
        return false;
    }


    //Login Credentials
    public boolean login(String email, String password) {
        Optional<UserModel> userModel = userRepository.findById(email);
        if (userModel.isPresent()) {
            return encoder.matches(password, userModel.get().getPassword());
        }
        return false;
    }
}
