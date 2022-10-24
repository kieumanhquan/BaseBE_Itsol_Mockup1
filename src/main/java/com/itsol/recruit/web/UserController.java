package com.itsol.recruit.web;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.ResponseObject;
import com.itsol.recruit.entity.Role;
import com.itsol.recruit.entity.Unit;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.UnitService;
import com.itsol.recruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)

public class UserController {

    @Autowired
    UnitService unitService;

    @Autowired
    UserRepository userRepository;

    public final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok().body(userService.findAll());

    }

    //Trang code get emp
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    //trang code add emp
    @PostMapping("/user")
    public ResponseEntity<ResponseObject> create(@RequestBody User user) {
        System.out.println();
        if (userService.findUserByUserName(user.getUserName()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Username đã tồn tại", ""));
        }
        if (userService.findUserByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Email đã tồn tại", ""));
        }
        if (userService.findUserByCCCD(user.getCccd()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "CCCD đã tồn tại", ""));
        }
        if (userService.findUserByPhoneNumber(user.getPhoneNumber()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Phone đã tồn tại", ""));
        }
        String newPass = user.getPassword();
        user.setPassword(passwordEncoder.encode(newPass));
        userService.save(user);
        return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, "Tạo user mới thành công", ""));
    }

    //trang code update emp
    @PutMapping("/user/{id}")
    public ResponseEntity<ResponseObject> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        User userDB = userService.findById(id);

        if (userService.findUserByUserName(user.getUserName()) != null && !userDB.getUserName().equals(user.getUserName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Username đã tồn tại", ""));
        }
        if (userService.findUserByEmail(user.getEmail()) != null && !userDB.getEmail().equals(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Email đã tồn tại", ""));
        }
        if (userService.findUserByCCCD(user.getCccd()) != null && !userDB.getCccd().equals(user.getCccd())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "CCCD đã tồn tại", ""));
        }
        if (userService.findUserByPhoneNumber(user.getPhoneNumber()) != null && !userDB.getPhoneNumber().equals(user.getPhoneNumber())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Phone đã tồn tại", ""));
        }

        userDB.setFullName(user.getFullName());
        userDB.setEmail(user.getEmail());
        userDB.setUserName(user.getUserName());
        userDB.setCccd(user.getCccd());
        userDB.setLiteracy(user.getLiteracy());
        userDB.setPhoneNumber(user.getPhoneNumber());
        userDB.setHomeTown(user.getHomeTown());
        userDB.setSalary(user.getSalary());
        userDB.setGender(user.getGender());
        userDB.setPosition(user.getPosition());
        userDB.setLeader(user.isLeader());
        userDB.setBirthDay(user.getBirthDay());
        userDB.setActive(user.isActive());


        userService.update(userDB);
        return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, "Cập nhật user thành công", ""));
    }


    @GetMapping("/getAllUnit")
    public ResponseEntity<List<Unit>> getAll() {
        return ResponseEntity.ok().body(unitService.findAll());
    }


    @PutMapping("/user/sortByKey")
    public ResponseEntity<ResponseObject> sortByKey(@RequestParam int page,
                                                    @RequestParam Long id,
                                                    @RequestParam(required = true) String sortByValue,
                                                    @RequestParam(required = true) String descAsc,
                                                    @RequestBody UserDTO dto) {
        Pageable pageable;
        User u = userService.findById(id);
        Set<Role> roles = u.getRoles();

        Set<String> listRole = roles.stream().map(role -> role.getCode()).collect(Collectors.toSet());
        try {
            if (page < 0) {
                page = 0;
            }
            if (sortByValue.equals("undefined")) {
                sortByValue = "name";
            }
            if (descAsc.equals("desc")) {
                pageable = PageRequest.of(page, 5, Sort.by(sortByValue).descending());
            } else {
                pageable = PageRequest.of(page, 5, Sort.by(sortByValue).ascending());
            }

            System.out.println(dto.getBirthDay());
            if (listRole.contains("ROLE_ADMIN") || listRole.contains("ROLE_HR")
                    || listRole.contains("ROLE_DM_HR")) {

                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(HttpStatus.OK, "Tìm thấy thành công",
                                userService.sortByKey(pageable, dto.getFullName(), dto.getEmail(), dto.getLiteracy(), dto.getPosition(), dto.getSalary(), dto.getBirthDay(), dto.getUnit(), null)));
            } else if (listRole.contains("ROLE_DM")) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(HttpStatus.OK, "Tìm thấy thành công",
                                userService.sortByKey(pageable, dto.getName(), dto.getEmail(), dto.getLiteracy(), dto.getPosition(),
                                        dto.getSalary(), dto.getBirthDay(), dto.getUnit(), u.getUnit())));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Không tìm thấy!", ""));


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Không tìm thấy!", ""));
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

    @GetMapping(value = "user/find-dm-by-unit/{id}")
    public ResponseEntity<User> findDMbyUnit(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok(userService.findDMByUnitId(id));
        } catch (Exception e) {
            return ResponseEntity.ok().build();
        }
    }


}
