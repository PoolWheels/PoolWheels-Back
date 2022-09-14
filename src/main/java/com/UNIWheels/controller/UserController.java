package com.UNIWheels.controller;

import com.UNIWheels.dto.UserDriverDTO;
import com.UNIWheels.dto.UserDriverDTO;
import com.UNIWheels.dto.UserTravelerDTO;
import com.UNIWheels.entities.UserDriver;
import com.UNIWheels.entities.UserTraveler;
import com.UNIWheels.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/driver")
    public ResponseEntity<UserDriver> createrUserDriver(@RequestBody UserDriverDTO userDTO){
        return ResponseEntity.ok( userService.createUserDriver(userDTO));

    }

    @PostMapping("/traveler")
    public ResponseEntity<UserTraveler> createrUserTraveler(@RequestBody UserTravelerDTO userTravelerDTO){
        return ResponseEntity.ok( userService.createUserTraveler(userTravelerDTO));

    }



}
