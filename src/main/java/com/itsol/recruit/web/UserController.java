package com.itsol.recruit.web;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.ResponseObject;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)

public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")

    public ResponseEntity<List<User>> getAllUserName() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    //Trang code qlnv
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }
//trang code qlnv
    @PostMapping("/user")
    public ResponseEntity<ResponseObject> create(@RequestBody User user) {
        try {
            userService.save(user);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(HttpStatus.OK, "Thêm nhân viên thành công")
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Thêm nhân viên thất bại", ""));
        }
    }

    ///

    @PutMapping(value = "/user")
    public ResponseEntity<User> update(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.update(user));
        } catch (Exception e) {
            return ResponseEntity.ok().build();
        }
    }

    //    @PutMapping(value = "{user/updating}")
//    public ResponseEntity<User> updateinfo(@RequestParam("username") String  email, @RequestBody UserDTO userDTO){
//        try {
//
//        } catch (Exception e) {
//            return ResponseEntity.ok().build();
//        }
//    }
    @GetMapping(value = "/user/username/{username}")
    public ResponseEntity<User> findUserByUserName(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.findUserByUserName(username));
    }


}
