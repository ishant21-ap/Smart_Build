package com.example.Registeration.controller;

import com.example.Registeration.model.AdminModel;
import com.example.Registeration.model.TemporaryUser;
import com.example.Registeration.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @PostMapping("/otpsend")
    public String sendOtp(@RequestBody TemporaryUser temporaryUser){
        if(adminService.generateOtpAndSendEmail(temporaryUser)){
            return "OTP sent successfully";
        }
        return "Failed to sent OTP";
    }



    @PostMapping("/verifyotp")
    public String verifyOtp(@RequestBody TemporaryUser temporaryUser){
        if(adminService.verifyOtp(temporaryUser)){
            return "OTP verified successfully";
        }
        return "Failed to verify OTP";
    }



    @PostMapping("/register")
    public String register(@RequestBody AdminModel adminModel){
        if(adminService.registerAdmin(adminModel)){
            return "Admin registered successfully";
        }
        return "Failed to register Admin";
    }



    @PostMapping("/login")
    public String login(@RequestBody TemporaryUser temporaryUser){
        if(adminService.loginAdmin(temporaryUser.getEmail(), temporaryUser.getOtp())){
            return "Admin logged in successfully";
        }
        return "Failed to login Admin";
    }
}
