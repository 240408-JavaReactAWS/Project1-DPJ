package com.revature.Project1DPJ.controllers;

import com.revature.Project1DPJ.DTO.LoginDTO;
import com.revature.Project1DPJ.DTO.UserDTO;
import com.revature.Project1DPJ.models.UserModel;
import com.revature.Project1DPJ.services.UserServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
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

    @PostMapping("login")
    public ResponseEntity<LoginDTO> loginNewUserHandler(@RequestBody LoginDTO user){
        LoginDTO loggedInUser = userServices.loginUser(user.getEmail(),user.getPassword());
        if (loggedInUser != null){
            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") int userId) {
        UserDTO userModel=userServices.getUserById(userId);
        if (userModel != null){
            return new ResponseEntity<>(userModel, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDto) {
        // Check if the email is already registered
        if (userServices.getUserByEmail(userDto.getEmail())!= null) {
            return ResponseEntity.badRequest().body("Email is already registered");
        }

        // Create a new user entity
        UserModel user = new UserModel();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getEmail()); // You should hash the password before saving

        // Save the user to the database
        userServices.saveUser(user);

        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(userServices.getAllUsers(),HttpStatus.OK);
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
