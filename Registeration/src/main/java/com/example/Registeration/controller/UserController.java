package com.example.Registeration.controller;

import com.example.Registeration.model.TemporaryUser;
import com.example.Registeration.model.UserModel;
import com.example.Registeration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/otpsend")
    public String sendOtp(@RequestBody TemporaryUser temporaryUser){
        if(userService.generateOtpAndSendEmail(temporaryUser)){
            return "OTP sent Successfully";
        }
        return "Failed to send OTP";
    }



    @PostMapping("/verifyotp")
    public String verifyOtp(@RequestBody TemporaryUser temporaryUser){
        if(userService.verifyOtp(temporaryUser)){
            return "OTP verified Successfully";
        }
        return "Failed to verify OTP";
    }



    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel){
        if(userService.registerUser(userModel)){
            return "User registered successfully";
        }
        return "Failed to register user";
    }



    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestBody TemporaryUser temporaryUser){
        if(userService.forgotPassword(temporaryUser.getEmail())){
            return "Otp sent for password reset";
        }
        return "Email not registered";
    }



    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody TemporaryUser temporaryUser){
        if(userService.resetPassword(temporaryUser.getEmail(),temporaryUser.getOtp())){
            return "Password reset successfully";
        }
        return "Invalid Email or OTP";
    }



    @PostMapping("/login")
    public String login(@RequestBody TemporaryUser temporaryUser){
        if(userService.login(temporaryUser.getEmail(), temporaryUser.getOtp())){
            return "Login successful";
        }
        return "Invalid Email or password";
    }
}
