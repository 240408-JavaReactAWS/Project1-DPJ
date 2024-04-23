package com.revature.Project1DPJ.services;


import com.revature.Project1DPJ.models.User;
import com.revature.Project1DPJ.repos.UserDAO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserDAO userDAO;


    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean patchUserAccountPassword(int id, String password) {

        Optional<User> optionalUser = this.userDAO.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(password);
            this.userDAO.save(user);
            return true;
        }
        return false;
    }

}
