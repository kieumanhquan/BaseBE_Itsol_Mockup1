package com.itsol.recruit.web;

import com.itsol.recruit.entity.ResponseObject;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/")
public class ChangePassController {

    @Autowired
    UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PutMapping("changepass/{USER_ID}")
    public ResponseEntity<ResponseObject> changePass(@PathVariable("USER_ID") Long userid, @RequestBody String password) {
        try {
            User u = userService.findById(userid);
            u.setPassword(passwordEncoder.encode(password));
            userService.update(u);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("true", "Thay đổi mật khẩu thành công", u)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("false", "Thay đổi mật khẩu thất bại", ""));
        }
    }
}
