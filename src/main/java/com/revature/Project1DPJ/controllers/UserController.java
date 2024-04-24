package com.revature.Project1DPJ.controllers;

import com.revature.Project1DPJ.models.UserModel;
import com.revature.Project1DPJ.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping
    public ResponseEntity<UserModel> addUser(@RequestBody UserModel model) {
        UserModel userModel=userServices.saveUser(model);
        if (userModel != null){
            return new ResponseEntity<>(userModel, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserModel> getUserById(@PathVariable int userId) {
        UserModel userModel=userServices.getUserById(userId);
        if (userModel != null){
            return new ResponseEntity<>(userModel, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers(){
        return new ResponseEntity<>(userServices.getAllUsers(),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserModel> updateUser(@RequestBody UserModel model){
        UserModel userModel=userServices.saveUser(model);
        if (userModel != null){
            return new ResponseEntity<>(userModel, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
