package com.itsol.recruit.web;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)

public class UserController {

    public final UserService userService ;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<User>> getAllUser(){
        return  ResponseEntity.ok().body( userService.getAllUser());
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> findUserById(@RequestParam("id") Long id){
        return  ResponseEntity.ok().body( userService.findById(id));
    }

    @PutMapping(value = "/user")
    public ResponseEntity<User> update(@RequestBody User user){
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

}
