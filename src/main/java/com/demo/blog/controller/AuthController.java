package com.demo.blog.controller;

import com.demo.blog.entities.Role;
import com.demo.blog.entities.User;
import com.demo.blog.payload.LoginDto;
import com.demo.blog.payload.SignUpDto;
import com.demo.blog.repositry.RoleRepositry;
import com.demo.blog.repositry.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepositry roleRepositry;

    @Autowired
    private UserRepositry userRepositry;

    @Autowired
    private AuthenticationManager authenticationManager;


//    @PostMapping("/signin")
//    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
//                (loginDto.getUsernameOrEmail(),loginDto.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return new ResponseEntity<>("User signed in succesfully",HttpStatus.OK);
//
//    }
@PostMapping("/signin")
public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
            (loginDto.getUsername(),loginDto.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return new ResponseEntity<>("User signed in succesfully",HttpStatus.OK);
}



    //Create LoginDto
    //Create AuthController
    //create CustomUserDetailService
    //UpdateSecurity Config




    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){

        boolean b = userRepositry.existsByEmail(signUpDto.getEmail());
        if (b) {
            return new ResponseEntity<>("Duplicate email", HttpStatus.BAD_REQUEST);
        }

        boolean b1 = userRepositry.existsByUsername(signUpDto.getUsername());
        if (b1) {
            return new ResponseEntity<>("Duplicate username", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role roles = roleRepositry.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));
       userRepositry.save(user);
        return new ResponseEntity<>("user is registered", HttpStatus.CREATED);



    }

}
