package com.example.oauth.controller;


import com.example.oauth.model.RegisterCommand;
import com.example.oauth.model.Role;
import com.example.oauth.model.User;
import com.example.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class HomeController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody RegisterCommand registerCommand){

        System.out.println(registerCommand.getUsername()+" : "+registerCommand.getPassword());

        User user = userService.findByUsername(registerCommand.getUsername());
        if (user == null){
            User userToRegister = new User(registerCommand.getUsername(),registerCommand.getPassword(),2000,30, Arrays.asList(new Role("ROLE_USER")));
            BCryptPasswordEncoder bcEncoder = new BCryptPasswordEncoder();
            userToRegister.setPassword(bcEncoder.encode(userToRegister.getPassword()));
            userService.save(userToRegister);
            return ResponseEntity.ok(HttpStatus.OK);
        } else return ResponseEntity.ok(HttpStatus.BAD_REQUEST);


    }
}
