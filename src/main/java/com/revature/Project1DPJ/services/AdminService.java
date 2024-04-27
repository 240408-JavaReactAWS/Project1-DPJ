package com.revature.Project1DPJ.services;

import com.revature.Project1DPJ.repos.AdminDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminDAO adminDAO;

    public AdminService() {
    }

    public AdminService(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
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
