package com.demo.blog.controller;

import com.demo.blog.entities.Role;
import com.demo.blog.entities.User;
import com.demo.blog.payload.SignUpDto;
import com.demo.blog.repositry.RoleRepositry;
import com.demo.blog.repositry.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
//        Role roles = roleRepositry.findByName("ROLE_ADMIN").get();
//        user.setRoles(Collections.singleton(roles));
       userRepositry.save(user);
        return new ResponseEntity<>("user is registered", HttpStatus.CREATED);



    }

}
