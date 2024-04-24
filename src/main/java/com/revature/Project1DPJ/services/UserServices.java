package com.revature.Project1DPJ.services;

import com.revature.Project1DPJ.models.UserModel;
import com.revature.Project1DPJ.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {
    public UserRepository userRepository;

    public UserServices() {
    }

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

    public UserModel saveUser(UserModel user) {

            return userRepository.save(user);

    }

    public List<UserModel> getAllUsers(){
        return userRepository.findAll();
    }
}
