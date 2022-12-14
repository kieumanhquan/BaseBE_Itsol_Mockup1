package com.itsol.recruit.web.auth;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.PasswordBean;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.security.jwt.JWTFilter;
import com.itsol.recruit.security.jwt.TokenProvider;
import com.itsol.recruit.service.AuthenticateService;
import com.itsol.recruit.service.UserService;
import com.itsol.recruit.utils.EncryptUtil;
import com.itsol.recruit.web.vm.LoginVM;
import io.swagger.annotations.Api;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = Constants.Api.Path.AUTH)
@Api(tags = "Auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticateController {

    private final AuthenticateService authenticateService;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UserService userService;

    private final TokenProvider tokenProvider;

    public AuthenticateController(AuthenticateService authenticateService, AuthenticationManagerBuilder authenticationManagerBuilder, UserService userService, TokenProvider tokenProvider) {
        this.authenticateService = authenticateService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@Valid @RequestBody UserDTO dto) {
        return ResponseEntity.ok().body(authenticateService.signup(dto));
    }

    /*
    Login api
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateAdmin(@Valid @RequestBody LoginVM loginVM) {
//		T???o chu???i authentication t??? username v?? password (object LoginRequest
//		- file n??y ch??? l?? 1 class b??nh th?????ng, ch???a 2 tr?????ng username v?? password)
        UsernamePasswordAuthenticationToken authenticationString = new UsernamePasswordAuthenticationToken(
                loginVM.getUserName(),
                loginVM.getPassword()
        );
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationString);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, loginVM.getRememberMe());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, String.format("Bearer %s", jwt));
        return new ResponseEntity<>(Collections.singletonMap("token", jwt), httpHeaders, HttpStatus.OK); //Tr??? v??? chu???i jwt(authentication string)

//        User userLogin = userService.findUserByUserName(adminLoginVM.getUserName());
//        return ResponseEntity.ok().body(new JWTTokenResponse(jwt, userLogin.getUserName())); //Tr??? v??? chu???i jwt(authentication string)

    }
    @PutMapping("/rest/{id}")
    public ResponseEntity<?> changePassword(@PathVariable("id") Long id,@Valid @RequestBody PasswordBean passwordBean){
        try {
            User user = userService.findById(id);
            Boolean checkPassOld = EncryptUtil.check(passwordBean.getPasswordOld(), user.getPassword());
            Map<String, String> mapError = new HashMap<>();
            if(!checkPassOld){
                mapError.put("passwordOld", "Old password is not correct !");
                return ResponseEntity.badRequest().body(mapError);
            }else if(!passwordBean.getPasswordNew().equals(passwordBean.getConfirmPassword())) {
                mapError.put("confirmPassword", "Confirmation password is incorrect !");
                return ResponseEntity.badRequest().body(mapError);
            }
            String passwordHash = EncryptUtil.encrypt(passwordBean.getPasswordNew());
            user.setPassword(passwordHash);
            return ResponseEntity.ok(userService.update(user));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
