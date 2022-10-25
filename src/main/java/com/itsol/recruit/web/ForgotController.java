package com.itsol.recruit.web;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.Otp;
import com.itsol.recruit.entity.ResponseObject;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.OtpRepository;
import com.itsol.recruit.service.EmailSenderService;
import com.itsol.recruit.service.OtpService;
import com.itsol.recruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class ForgotController {
    @Autowired
    UserService userService;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    OtpService otpService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    OtpRepository otpRepository;


    @GetMapping("/forgot/{email}")
    public ResponseEntity<ResponseObject> sendMail(@PathVariable("email") String email) {
        for (User x : userService.findAll()
        ) {
            if (x.getEmail().equals(email) && x.isActive() == true) {
                Random random = new Random();
                int otp = random.nextInt(900000) + 100000;
                System.out.println(x.getId());
                Otp ot = new Otp();
                ot.setCode(otp);
                ot.setUserid(x.getId());
                ot.setStatus(1);
                ot.setIssue_At(System.currentTimeMillis() + 300000);
                otpService.save(ot);
                emailSenderService.sendSimpleEmail(email, "OTP code", "Your OTP code is: " + otp);
                System.out.println("OTP da duoc gui den email cua ban!");
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("true", "OTP da duoc gui den email cua ban!", x)
                );
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("false", "Email ko ton tai!", email)
        );
    }


    @GetMapping("/forgot/checkotp")
    public ResponseEntity<?> findByUserId(@RequestParam("USER_ID") Long userId,
                                          @RequestParam("CODE") Integer code){
        Otp otp = otpRepository.findByUserId(userId,code);
        if(otpRepository.findByUserId(userId,code) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("true","Mã OTP không đúng","")
            );
        } else if (otp.getIssue_At() <= new Date().getTime()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("false", "Otp này đã hết hạn!", ""));
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

}
