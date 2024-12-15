package com.example.Registeration.service;

import com.example.Registeration.model.AdminModel;
import com.example.Registeration.model.TemporaryUser;
import com.example.Registeration.repository.AdminRepository;
import com.example.Registeration.repository.TemporaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private TemporaryRepository temporaryRepository;

    @Autowired
    private EmailService emailService;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private Random random = new Random();


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



    public boolean registerAdmin(AdminModel adminModel){
        if(adminRepository.findById(adminModel.getEmail()).isPresent()){
            return false;
        }
        adminModel.setPassword(encoder.encode(adminModel.getPassword()));
        adminRepository.save(adminModel);
        return true;
    }


    public boolean loginAdmin(String email, String password){
        Optional<AdminModel> adminModel = adminRepository.findById(email);
        if(adminModel.isPresent()){
            return encoder.matches(password, adminModel.get().getPassword());
        }
        return false;
    }




}
