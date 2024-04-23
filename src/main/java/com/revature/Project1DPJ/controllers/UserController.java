package com.revature.Project1DPJ.controllers;

import com.revature.Project1DPJ.models.UserModel;
import com.revature.Project1DPJ.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping
    public String addUser(@RequestBody UserModel user) {
        return userServices.saveUser(user);

    }

    @GetMapping("/{userId}")
    public UserModel getUserById(@PathVariable int userId) {
        return userServices.getUserById(userId);
    }


}
