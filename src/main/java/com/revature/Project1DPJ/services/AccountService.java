package com.revature.Project1DPJ.services;

import com.revature.Project1DPJ.models.Account;
import com.revature.Project1DPJ.repos.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountService {

    private AccountDAO accountDAO;

    @Autowired
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }


    public List<Account> getAllAccounts() {
        return this.accountDAO.findAll();
    }

}
