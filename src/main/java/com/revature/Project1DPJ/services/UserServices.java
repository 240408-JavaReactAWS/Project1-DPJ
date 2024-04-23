package com.revature.Project1DPJ.services;

import com.revature.Project1DPJ.models.UserModel;
import com.revature.Project1DPJ.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    private UserRepository userRepository;

    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel getUserById(int useId) {
        return userRepository.findUserById(useId);

    }
    public UserModel getUserByUsername(String firstName) {
        return userRepository.findUserByFirstName(firstName);
    }

    public String saveUser(UserModel user){
        if(user != null){
            userRepository.save(user);
            return "succesfully added";
        }
        return "failed to save user";

    }
}
