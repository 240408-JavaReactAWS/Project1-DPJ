package com.revature.Project1DPJ.services;

import com.revature.Project1DPJ.models.Account;
import com.revature.Project1DPJ.models.Admin;
import com.revature.Project1DPJ.models.User;
import com.revature.Project1DPJ.models.UserModel;
import com.revature.Project1DPJ.repos.AdminDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private AdminDAO adminDAO;

    @Autowired
    public AdminService(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }


    public List<Admin> getAllAdmins() {
        return this.adminDAO.findAll();
    }

    public Admin createAdminAccount(Admin admin) {
        return this.adminDAO.save(admin);
    }

    public Admin login(String username, String password) {
        return this.adminDAO.findByUsernameAndPassword(username, password);
    }

//    public boolean resetUserAccountPassword(int id, String password) {
//        Optional<Account> optionalAccount = this.accountDAO.findById(id);
//        if (optionalAccount.isPresent()) {
//            Account account = optionalAccount.get();
//            UserModel userId = account.getAccountOwner();
//
//            Optional<User> optionalUser = this.userDAO.findById(id);
//            if (optionalUser.isPresent()) {
//                User user = optionalUser.get();
//                user.setPassword(password);
//                this.userDAO.save(user);
//                return this.userDAO.existsById(user.getId());
//            }
//            else {
//                return false;
//            }
//        }
//        return false;
//    }
}
