package com.revature.Project1DPJ.controllers;

import com.revature.Project1DPJ.DTO.SRDTO;
import com.revature.Project1DPJ.DTO.UserDTO;
import com.revature.Project1DPJ.models.AccountType;
import com.revature.Project1DPJ.models.UserModel;
import com.revature.Project1DPJ.services.UserServices;
import com.revature.Project1DPJ.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000")
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

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") int userId) {
        UserDTO userModel=userServices.getUserById(userId);
        if (userModel != null){
            return new ResponseEntity<>(userModel, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }
    @GetMapping("user")
    public ResponseEntity<SRDTO> getUserByEmail(@RequestParam(value="email") String email, boolean userToUser) {
        UserModel userModel=userServices.getUserByEmail(email);
        if (userModel != null){

            int checkingAccountNumber=userModel.getAccount(AccountType.valueOf("CHECKING")).getAccountNumber();
            int savingsAccountNumber=userModel.getAccount(AccountType.valueOf("SAVINGS")).getAccountNumber();
            UserDTO userDTO=UserUtil.mapUserToUserDTO(userModel);
            SRDTO sr;
            if(userToUser){sr= UserUtil.mapUserDTOToSRDTO(userDTO, checkingAccountNumber,savingsAccountNumber);}
            else{sr= UserUtil.mapUserDTOToSRDTO(userDTO, checkingAccountNumber);}
            return new ResponseEntity<>(sr, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


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
