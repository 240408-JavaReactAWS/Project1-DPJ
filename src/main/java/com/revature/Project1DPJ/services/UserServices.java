package com.revature.Project1DPJ.services;

import com.revature.Project1DPJ.DTO.UserDTO;
import com.revature.Project1DPJ.models.Account;
import com.revature.Project1DPJ.models.UserModel;
import com.revature.Project1DPJ.models.UserStatus;
import com.revature.Project1DPJ.models.UserType;
import com.revature.Project1DPJ.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    public UserRepository userRepository;

    public UserServices() {
    }

    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }




    public UserDTO getUserById(int useId) {
        UserModel user = userRepository.findUserById(useId);
        return this.mapUserToUserDTO(user);

    }

    public UserDTO getUserByUsername(String firstName) {
        UserModel model = userRepository.findUserByFirstName(firstName);
        return this.mapUserToUserDTO(model);
    }

    public UserDTO saveUser(UserModel user) {
        UserModel foundUser;
        foundUser=userRepository.findUserById(user.getId());
        if(foundUser==(null)){
            user.setUserStatus(UserStatus.UNLOCKED);
            user.setRole(UserType.USER);
            UserModel model = userRepository.save(user);
            return this.mapUserToUserDTO(model);
        }else{
            if(!foundUser.getFirstName().equals(user.getFirstName())){
                foundUser.setFirstName(user.getFirstName());
            }
            if(!foundUser.getLastName().equals(user.getLastName())){
                foundUser.setLastName(user.getLastName());
            }
            if(!foundUser.getEmail().equals(user.getEmail())){
                foundUser.setEmail(user.getEmail());
            }

            if(!foundUser.getPassword().equals(user.getPassword())){
                foundUser.setPassword(user.getPassword());
            }
            UserModel model=userRepository.save(foundUser);
            return this.mapUserToUserDTO(model);

        }
//        return null;

    }

    public UserDTO getUserByEmail(String email){
        UserModel model = userRepository.findUserByEmail(email);
        if(model != null){
            return this.mapUserToUserDTO(model);
        }
        return null;
    }

    public List<UserDTO> getAllUsers(){
        List<UserDTO>allUsersDTO=new ArrayList<>();
        List<UserModel>allUsers=userRepository.findAll();
        if(!allUsers.isEmpty()) {
            for (UserModel user : allUsers) {
                allUsersDTO.add(this.mapUserToUserDTO(user));
            }
        }
        return allUsersDTO;
    }

    public boolean patchUserAccountPassword(int id, String password) {

        Optional<UserModel> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            user.setPassword(password);
            this.userRepository.save(user);
            return true;
        }
        return false;
    }


    public boolean patchUserStatus(int id, UserStatus userStatus) {
        Optional<UserModel> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            user.setUserStatus(userStatus);
            this.userRepository.save(user);
            return true;
        }
        return false;
    }

    public  UserModel mapUserDTOToUser(UserDTO user){
        return userRepository.findUserByEmail(user.getEmail());
    }

    public UserDTO mapUserToUserDTO(UserModel user){
        return new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail());
    }
}
