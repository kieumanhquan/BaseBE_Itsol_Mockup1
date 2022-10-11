package com.itsol.recruit.web;

import com.itsol.recruit.entity.Otp;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.service.EmailSenderService;
import com.itsol.recruit.service.OtpService;
import com.itsol.recruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/public/forgot/")
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
            System.out.println(x.getEmail());
            if (x.getEmail().equals(email) && x.isActive() == true) {
                Random random = new Random();
                int otp = random.nextInt(900000) + 100000;
                System.out.println(x.getId());
                Otp ot = new Otp();
                ot.setCode(otp);
                ot.setUserid(x.getId());
                ot.setStatus(1);
                otpService.save(ot);
                emailSenderService.sendSimpleEmail(email, "OTP code", "Your OTP code is: " + otp);
                System.out.println("OTP da duoc gui den email cua ban!");
                return true;
            }
            System.out.println("Khong co email nay trong he thong");
            return false;
        }
        return false;
    }


    @GetMapping("getotp")
    public List<Otp> getOtp(@RequestParam("email") String email) {
        System.out.println(email);
        return otpService.checkOtp(email);
    }


    @PutMapping("/changepass")
    public User changePass(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        User user = userService.findUserByEmail(email);
        user.setPassword(password);
        return userService.updateUser(user);
    }


}
