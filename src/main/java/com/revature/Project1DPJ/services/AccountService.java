package com.revature.Project1DPJ.services;

import com.revature.Project1DPJ.models.Account;
import com.revature.Project1DPJ.models.AccountStatus;
import com.revature.Project1DPJ.models.User;
import com.revature.Project1DPJ.models.UserModel;
import com.revature.Project1DPJ.repos.AccountDAO;
import com.revature.Project1DPJ.repos.TransactionDAO;
import com.revature.Project1DPJ.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;


@Service
public class AccountService {

    private AccountDAO accountDAO;
    private UserRepository userDAO;
    private TransactionDAO transactionDAO;


    public AccountService() {}

    @Autowired
    public AccountService(AccountDAO accountDAO, UserRepository userDAO, TransactionDAO transactionDAO) {
        this.accountDAO = accountDAO;
        this.userDAO = userDAO;
        this.transactionDAO = transactionDAO;
    }

    public Account saveAccount(Account account){
        int id = account.getAccountOwner().getId();
        //UserModel user = userDAO.getById(id);
        UserModel user = userDAO.findUserById(id);
        if(user!=null){
            user.setId(id);
            account.setAccountOwner(user);
            account.setAccountTransactions(transactionDAO.findAllTransactionsByAccount(account.getId()));
        }
        return accountDAO.save(account);
    }


    public List<Account> getAllAccounts() {
        return this.accountDAO.findAll();
    }


    public Account getUserAccountById(int id) {
        Optional<Account> optionalAccount = this.accountDAO.findById(id);
        return optionalAccount.orElse(null);
    }


    public boolean patchAccountStatus (int id, AccountStatus accountStatus) {
        Optional<Account> optionalAccount =  this.accountDAO.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setAccountStatus(accountStatus);
            this.accountDAO.save(account);
            return true;
        }
        return false;
    }

//    public int deleteUserAccountById(int id) {
//
//        if (!this.accountDAO.existsById(id)) return 0;
//        this.accountDAO.deleteById(id);
//        if (this.accountDAO.existsById(id)) return 1;
//        else {
//            return 2;
//        }
//    }

}
