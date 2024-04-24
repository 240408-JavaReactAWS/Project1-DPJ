package com.revature.Project1DPJ.services;

import com.revature.Project1DPJ.models.Account;
import com.revature.Project1DPJ.models.User;
import com.revature.Project1DPJ.repos.AccountDAO;
import com.revature.Project1DPJ.repos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;


@Service
public class AccountService {

    private AccountDAO accountDAO;
    private UserDAO userDAO;

    @Autowired
    public AccountService(AccountDAO accountDAO, UserDAO userDAO) {
        this.accountDAO = accountDAO;
        this.userDAO = userDAO;
    }


    public List<Account> getAllAccounts() {
        return this.accountDAO.findAll();
    }


    public Account getUserAccountById(int id) {
        Optional<Account> optionalAccount = this.accountDAO.findById(id);
        return optionalAccount.orElse(null);
    }



    public boolean deleteUserAccountById(int id) {

        if (!this.accountDAO.existsById(id)) return true;

        this.accountDAO.deleteById(id);

        // if account object exists in the database, return false. Otherwise, return true
        return !this.accountDAO.existsById(id);
    }


    public boolean resetUserAccountPassword(int id, String password) {
        Optional<Account> optionalAccount = this.accountDAO.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            int userId = account.getAccountOwnerId();

            Optional<User> optionalUser = this.userDAO.findById(userId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setPassword(password);
                this.userDAO.save(user);
                return this.userDAO.existsById(user.getId());
            }
            else {
                return false;
            }
        }
        return false;
    }
}
