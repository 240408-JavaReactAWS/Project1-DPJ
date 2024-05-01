package com.revature.Project1DPJ.controllers;

import com.revature.Project1DPJ.DTO.UserDTO;
import com.revature.Project1DPJ.models.Account;
import com.revature.Project1DPJ.models.UserModel;
import com.revature.Project1DPJ.models.UserStatus;
import com.revature.Project1DPJ.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = {"http://localhost:3000"})
public class UserController {
    UserServices userServices;

    public UserController() {
    }

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }


    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody UserModel model) {
        UserDTO userModel=userServices.saveUser(model);
        if (userModel != null){
            return new ResponseEntity<>(userModel, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*
     * As a user, I should be able to log in into my account
     */
    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody UserModel model) {

        UserDTO userModel = userServices.findByEmailAndPassword(model.getEmail(), model.getPassword());

        if(userModel != null) return new ResponseEntity<>(userModel, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") int userId) {
        UserDTO userModel=userServices.getUserById(userId);
        if (userModel != null){
            return new ResponseEntity<>(userModel, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @PutMapping("{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") int id, @RequestBody UserModel model){
        if(userServices.getUserById(id)!=null){
            model.setId(id);
            UserDTO user = userServices.saveUser(model);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
