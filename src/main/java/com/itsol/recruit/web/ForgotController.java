package com.itsol.recruit.web;

import com.itsol.recruit.entity.Otp;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.service.EmailSenderService;
import com.itsol.recruit.service.OtpService;
import com.itsol.recruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/rest/forgot")
public class ForgotController {
    @Autowired
    UserService userService;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    OtpService otpService;
    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping("{email}")
    public boolean sendMail(@PathVariable("email") String email) {
        for (User x : userService.findAll()
        ) {
            if (x.getEmail().equals(email) && x.isActive() == true) ;
            Random random = new Random();
            int otp = random.nextInt(900000) + 100000;

            Otp ot = new Otp();
            ot.setCode(otp);
            ot.setUser(x);
            ot.setStatus(1);
            otpService.save(ot);
            emailSenderService.sendSimpleEmail(email, "OTP code", "Your OTP code is: " + otp);
            return true;
        }
        return false;
    }

    @PutMapping("/changepass")
    public ResponseEntity<String> changePass(@RequestBody User changepass){
        try {
            userService.updateUserPassword(changepass.getUserName(),
                    passwordEncoder.encode(changepass.getPassword()));
            return ResponseEntity.status(HttpStatus.OK).body("Thanh cong");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user null");
        }
    }
}
